import com.tenebraex8.aoc2022.*

var (stack, commands) = "5.inp".readSections().map { it.splitLine() }

val stackcount = stack.last().last().digitToInt()
stack = stack.subList(0, stack.size-1)
val stacks = Array(stackcount){ mutableListOf<Char>() }
val stacks2 = Array(stackcount){ mutableListOf<Char>() }

//Scanning
for(line in stack) {
    var idx = 0
    var stackidx = 0
    while(idx < line.length) {
        if(line[idx] == '[') {  //number
            idx++
            stacks[stackidx].add(0,line[idx])
            stacks2[stackidx].add(0,line[idx++])
            assert(line[idx++] == ']')
        }
        else idx += 3
        stackidx++
        if(idx < line.length) assert(line[idx++] == ' ')
    }
}
val cmds = commands.map { it.remove("move ").remove("from ").remove("to ").split(" ").map { it.toInt() } }


//Executing
for((count, source, target) in cmds)  {
    repeat(count){ stacks[target-1].push(stacks[source-1].pop()!!) }
}

//Get msg
stacks.map { it.peek() }.joinToString("").firstSolution()

//Executing
for((count, source, target) in cmds)  {
    val tmp = mutableListOf<Char>()
    repeat(count){ tmp.add(stacks2[source-1].pop()!!) }
    tmp.reversed().forEach { stacks2[target-1].push(it) }
}

//Get msg
stacks2.map { it.peek() }.joinToString("").secondSolution()