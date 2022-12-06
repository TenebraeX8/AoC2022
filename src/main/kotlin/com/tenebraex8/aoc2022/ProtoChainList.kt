package com.tenebraex8.aoc2022

class ProtoChainList<T>(var value:T, var next: ProtoChainList<T>? = null) {
    fun find(value: T): ProtoChainList<T>? = when{
            this.value == value -> this
            this.next != null -> this.next!!.find(value)
            else -> null
    }
    fun chainBack(node: ProtoChainList<T>){
        if(this.next == null) this.next = node
        else this.next!!.chainBack(node)
    }

    fun size():Int = 1 + (this.next?.size() ?: 0)

    fun toList(): List<T>{
        val target = mutableListOf<T>()
        var cur: ProtoChainList<T>? = this
        while(cur != null){
            target.add(cur.value)
            cur = cur.next
        }
        return target
    }

    operator fun plusAssign(other: ProtoChainList<T>) { this.chainBack(other) }
}