import com.tenebraex8.aoc2022.*

val inp = "8.inp".readLines().map { it.toCharArray().map { it.digitToInt() } }
val grid = inp.toArray2D()
val visibleGrid = Array2D(grid.n, grid.m){false}

fun gridIteration(target:Array2D<Int>, mask:Array2D<Boolean>, iterator: Iterator<ArrayIndex>, resetCondition: (ArrayIndex, ArrayIndex)->Boolean) {
    var lastIdx = ArrayIndex(-1, -1)
    var maxVal = -1
    iterator.forEach { idx->
        if(resetCondition.invoke(lastIdx, idx)) maxVal = -1
        lastIdx = idx
        if(target[idx] > maxVal) mask[idx] = true
        maxVal = maxOf(maxVal, target[idx])
    }
}

gridIteration(grid, visibleGrid, grid.left()) {last,cur -> last.row != cur.row}
gridIteration(grid, visibleGrid, grid.right()) {last,cur -> last.row != cur.row}
gridIteration(grid, visibleGrid, grid.up()) {last,cur -> last.col != cur.col}
gridIteration(grid, visibleGrid, grid.down()) {last,cur -> last.col != cur.col}

visibleGrid.count { it }.firstSolution()

val scenicScores = Array2D(grid.n, grid.m){0L}

fun lookAndCount(target: Array2D<Int>, start: ArrayIndex, indexInc: (ArrayIndex)->ArrayIndex): Long{
    var steps = 0L
    var idx = indexInc(start)
    while(target.inBounds(idx)){
        steps++
        if(target[idx] >= target[start]) break
        idx = indexInc(idx)
    }
    return steps
}

grid.indices().forEach {idx->
    val up = lookAndCount(grid, idx){it.incRow(-1)}
    val right = lookAndCount(grid, idx){it.incCol()}
    val down = lookAndCount(grid, idx){it.incRow()}
    val left = lookAndCount(grid, idx){it.incCol(-1)}
    scenicScores[idx] = up * right * down * left
}

scenicScores.maxOf { it }.secondSolution()