import com.tenebraex8.aoc2022.firstSolution
import com.tenebraex8.aoc2022.readSections
import com.tenebraex8.aoc2022.secondSolution
import com.tenebraex8.aoc2022.splitLine

val inp = "1.inp".readSections().map { it.splitLine().sumOf { it.toInt() } }
inp.max().firstSolution()
inp.sortedDescending().subList(0, 3).sum().secondSolution()


