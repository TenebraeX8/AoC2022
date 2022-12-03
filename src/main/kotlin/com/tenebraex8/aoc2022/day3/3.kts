import com.tenebraex8.aoc2022.firstSolution
import com.tenebraex8.aoc2022.readLines
import com.tenebraex8.aoc2022.secondSolution

val inp = "3.inp".readLines()
val compartments = inp.map { Pair(it.substring(0, it.length/2).toSet(), it.substring(it.length/2, it.length).toSet()) }
val overlaps = compartments.map { it.first.intersect(it.second) }.flatten()
val prioritized = overlaps.map { if(it < 'a') it - 'A' + 27 else it - 'a' + 1 }
prioritized.sum().firstSolution()

val groups = mutableListOf<Triple<Set<Char>,Set<Char>,Set<Char>>>()
for(idx in inp.indices step 3)
    groups.add(Triple(inp[idx].toSet(), inp[idx+1].toSet(),inp[idx+2].toSet()))

val overlaps2 = groups.map { it.first.intersect(it.second).intersect(it.third) }.flatten()
val prioritized2 = overlaps2.map { if(it < 'a') it - 'A' + 27 else it - 'a' + 1 }
prioritized2.sum().secondSolution()