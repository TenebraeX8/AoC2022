import com.tenebraex8.aoc2022.*
import java.lang.StringBuilder

val inp = "10.inp".readLines()

val stopCycle = 241
val regXValues = Array(stopCycle){0L}
regXValues[0] = 1
var cycle = 0
for (line in inp) {
    if(line.startsWith("noop")) {
        cycle++
        if(cycle >=stopCycle) break
        regXValues[cycle] = regXValues[cycle-1]
    }
    else {
        val value = line.splitTwo(" ").second.toLong()
        cycle+=2
        if(cycle >=stopCycle) break
        regXValues[cycle-1] = regXValues[cycle-2]
        regXValues[cycle] = regXValues[cycle-1] + value
    }

}
val targets = arrayOf(20, 60, 100, 140, 180, 220)
targets.sumOf { regXValues[it - 1] * it }.firstSolution()

val image = Array2D(6, 40){'.'}
image.indices().forEach {
    val idx = it.row * image.m + it.col
    image[it] = if(it in ArrayIndex(it.row, regXValues[idx].toInt()).kernel()) '#' else ' '
}
"".secondSolution()
val builder = StringBuilder()
image.lineIndices().forEach{l->
    image.colIndices().forEach { c ->
        builder.append(image[l, c])
    }
    builder.append("\n")
}
builder.doPrintln()