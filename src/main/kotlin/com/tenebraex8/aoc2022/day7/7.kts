import com.tenebraex8.aoc2022.day7.FileStateMachine
import com.tenebraex8.aoc2022.firstSolution
import com.tenebraex8.aoc2022.readLines
import com.tenebraex8.aoc2022.secondSolution

val inp = "7.inp".readLines()
val fsm = FileStateMachine(inp)
val root = fsm.parse()

fun getCountWithSizes(root: FileStateMachine.FolderNode, maxSize:Int):Int {
    val size = root.totalSize
    return if(size <= maxSize) size + root.children.sumOf { getCountWithSizes(it, maxSize) }
           else root.children.sumOf { getCountWithSizes(it, maxSize) }
}

getCountWithSizes(root, 100000).firstSolution()

fun getMinSize(root: FileStateMachine.FolderNode, targetSize:Int):Int{
    val size = root.totalSize
    val childrenMin = if(root.children.size > 0) root.children.minOf { getMinSize(it, targetSize) } else Int.MAX_VALUE
    return if(size < targetSize) childrenMin
    else minOf(size, childrenMin)
}
val targetSize = 30000000 - (70000000 - root.totalSize)
getMinSize(root, targetSize).secondSolution()