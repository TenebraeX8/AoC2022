package com.tenebraex8.aoc2022.day7

import com.tenebraex8.aoc2022.copy
import com.tenebraex8.aoc2022.remove
import com.tenebraex8.aoc2022.splitTwo

class FileStateMachine(val program: List<String>) {

    private fun buildTree(root: FolderNode, mapping: Map<String, FolderInfo>) {
        root.info.subdirs.forEach {
            val path = root.info.name.joinToString("/") + "/" + it.name
            val childinfo = mapping[path] ?: throw IllegalStateException("$path does not exist in dictionary")
            val childnode = FolderNode(childinfo)
            buildTree(childnode, mapping)
            root.children.add(childnode)
        }
    }

    fun parse(): FolderNode {
        val folders = mutableMapOf<String, FolderInfo>()
        var curPath = mutableListOf<String>()
        var idx = 0
        while(idx < program.size){
            val line = program[idx]
            if(line.startsWith("$ cd")) {
                when(val param = line.remove("$ cd ").trim()){
                    ".." -> curPath.removeAt(curPath.size-1)
                    "/" -> curPath = mutableListOf("/")
                    else -> curPath.add(param)
                }
                idx++
            }
            else if(line.startsWith("$ ls"))
            {
                idx++
                val curFileList = mutableListOf<FileInfo>()
                while(idx < program.size && !program[idx].startsWith("$")) curFileList.add(FileInfo.fromLine(program[idx++]))
                folders[curPath.joinToString("/")] = FolderInfo(curPath.copy(), curFileList)
            }
            else throw IllegalArgumentException("$line is not a command!")
        }
        val root = FolderNode(folders["/"]!!)
        buildTree(root, folders)
        return root
    }


    data class FileInfo(val isDir: Boolean, val name:String, val size: Int = 0){
        override fun toString() = "${if(isDir)"dir " else ""} $name($size)"

        companion object{
            fun fromLine(line:String) =
                if(line.startsWith("dir")) FileInfo(true, line.remove("dir ").trim())
                else {
                    val (size, name) = line.splitTwo(" ")
                    FileInfo(false, name, size.toInt())
                }
        }
    }

    data class FolderInfo(val name: List<String>, val files: List<FileInfo>){
        val subdirs get() = files.filter { it.isDir }
        val subfiles get() = files.filter { !it.isDir }

        val size get()=subfiles.sumOf { it.size }
    }

    data class FolderNode(val info: FolderInfo, val children: MutableList<FolderNode> = mutableListOf(), val parent:FolderNode? = null){
        val totalSize:Int get() = this.info.size + children.sumOf { it.totalSize }
    }
}