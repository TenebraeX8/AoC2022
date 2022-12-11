import com.tenebraex8.aoc2022.*
import com.tenebraex8.aoc2022.day11.Monkey

val inp = "11.inp".readSections()

val monkeys = inp.map { it.splitLine() }.map { Monkey.parse(it) }.sortedBy { it.id }

repeat(20){
    monkeys.forEach{ it.doInspection(monkeys)}
}
monkeys.map { it.inspected }.sortedDescending().subList(0,2).reduce { a, b -> a * b }.firstSolution()

val monkeys2 = inp.map { it.splitLine() }.map { Monkey.parse(it) }.sortedBy { it.id }
val moduloValue = monkeys2.map { it.divisor }.reduce{a,b -> a*b}
monkeys2.forEach { it.reduction = moduloValue }
repeat(10000){
    monkeys2.forEach{ it.doInspection(monkeys2, relief = false)}
}
monkeys2.map { it.inspected }.sortedDescending().subList(0,2).reduce { a, b -> a * b }.secondSolution()