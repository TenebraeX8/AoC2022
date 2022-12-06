import com.tenebraex8.aoc2022.ProtoChainList
import com.tenebraex8.aoc2022.firstSolution
import com.tenebraex8.aoc2022.readContent
import com.tenebraex8.aoc2022.secondSolution

val inp = "6.inp".readContent().toCharArray()

var root = ProtoChainList(inp.first())
var firstSolved = false
for(idx in 1 until inp.size){
    val found = root.find(inp[idx])
    val node = ProtoChainList(inp[idx])
    if(found != null) {
        if(found.next != null) {
            root = found.next!!
            root += node
        }
        else root = node
    }
    else root += node
    if((root.size() == 4) and !firstSolved){
        (idx + 1).firstSolution()
        firstSolved = true
    }
    if(root.size() == 14) {
        (idx + 1).secondSolution()
        break
    }
}