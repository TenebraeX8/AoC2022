package com.tenebraex8.aoc2022.day11

import com.tenebraex8.aoc2022.remove
import com.tenebraex8.aoc2022.splitTwo

class Monkey(val id: Int) {
    val items = mutableListOf<Long>()
    var operation: (Long)->Long = {it}
    var divisor = 1L
    var next = mutableMapOf<Boolean, Int>()

    var inspected = 0L
    var reduction = -1L

    fun doInspection(monkeys: List<Monkey>, relief: Boolean = true) {
        items.forEach {
            var newLevel = this.operation(it)
            if(relief) newLevel /= 3
            else newLevel %= this.reduction
            val monkeyId = next[(newLevel % divisor) == 0L]
            monkeys.find { it.id == monkeyId }!!.items.add(newLevel)
            inspected++
        }
        items.clear()
    }


    companion object{
        fun parse(target:List<String>):Monkey{
            val monkey = Monkey(target[0].remove("Monkey ").remove(":").toInt())

            //items
            target[1].remove("Starting items: ").trim().split(", ").forEach {
                monkey.items.add(it.toLong())
            }

            //Operation
            val op = target[2].remove("Operation: new = old ").trim().splitTwo(" ")
            if(op.second == "old"){
                if(op.first == "+") monkey.operation = {it + it}
                else monkey.operation = {it * it}
            }
            else{
                when(op.first){
                    "+" -> monkey.operation = {it + op.second.toLong()}
                    "*" -> monkey.operation = {it * op.second.toLong()}
                }
            }

            //divisible
            monkey.divisor = target[3].remove("Test: divisible by ").trim().toLong()

            //if true/false
            monkey.next[true] = target[4].remove("If true: throw to monkey ").trim().toInt()
            monkey.next[false] = target[5].remove("If false: throw to monkey ").trim().toInt()

            return monkey
        }
    }
}
