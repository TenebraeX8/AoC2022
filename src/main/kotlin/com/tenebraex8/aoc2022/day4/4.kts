import com.tenebraex8.aoc2022.*

val inp = "4.inp".readLines().map { it.splitTwo(",").mapping { it.splitTwo("-").mapping { it.toInt() }.toRange() } }

inp.count { it.first.encloses(it.second) or it.second.encloses(it.first) }.firstSolution()
inp.count { it.first.overlaps(it.second) or it.second.overlaps(it.first) }.secondSolution()