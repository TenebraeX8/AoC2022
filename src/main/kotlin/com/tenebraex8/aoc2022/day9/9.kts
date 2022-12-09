import com.tenebraex8.aoc2022.*
import java.lang.IllegalArgumentException

val inp = "9.inp".readLines().map { it.splitTwo(" ").mapTo { Pair(it.first, it.second.toInt()) } }

val visitedFirst = mutableSetOf<ArrayIndex>()
val visitedTenth = mutableSetOf<ArrayIndex>()
var curHead = ArrayIndex(0,0)
var curTails = Array(9){ArrayIndex(0,0)}
visitedFirst.add(curTails.first())
visitedTenth.add(curTails.last())
inp.forEach {(direction, steps) ->
    repeat(steps) {
        curHead = when (direction) {
            "R" -> curHead.incCol()
            "L" -> curHead.incCol(-1)
            "U" -> curHead.incRow(-1)
            "D" -> curHead.incRow()
            else -> throw IllegalArgumentException("$direction is no valid direction")
        }
        if (curHead !in curTails[0].adjacent()) {
            curTails[0] = curTails[0].stepTowards(curHead)
            visitedFirst.add(curTails[0])
        }
        for(i in 1 until curTails.size){
            if(curTails[i-1] !in curTails[i].adjacent()) curTails[i] = curTails[i].stepTowards(curTails[i-1])
        }
        visitedTenth.add(curTails.last())
    }
}
visitedFirst.size.firstSolution()
visitedTenth.size.secondSolution()