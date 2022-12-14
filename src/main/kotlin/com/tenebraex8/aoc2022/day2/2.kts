import com.tenebraex8.aoc2022.*
import java.lang.IllegalStateException

val winningMap = mapOf(0 to 1, 1 to 2, 2 to 0)
val losingMap = winningMap.entries.associate { (k,v) -> v to k }

val inp = "2.inp".readLines().map { it.splitTwo(" ") }
val values = inp.map { it.mapTo { Pair(it.first[0] - 'A', it.second[0] - 'X') } }
val choiceScore = values.sumOf { it.second + 1 }
val winscore = values.sumOf { when (it.second) {
    it.first -> 3                   //draw
    winningMap[it.first] -> 6       //winning
    else -> 0
}.toInt()
}
(winscore + choiceScore).firstSolution()

val choiceScore2 = values.sumOf { when (it.second) {
    0 -> losingMap[it.first]!! + 1      //lose
    1 -> it.first+1                     //draw
    2 -> winningMap[it.first]!! + 1     //winning
    else -> throw IllegalStateException()
}.toInt()
}
val winscore2 = values.sumOf { it.second * 3 }
(winscore2 + choiceScore2).secondSolution()
