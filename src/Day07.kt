fun main() {
    fun part1(input: List<String>): Long {
        val root = Dir("/", null)
        var currentDir = root
        input.forEach { line ->
            when {
                line == "\$ cd .." -> currentDir = currentDir.parent!!
                line == "\$ cd /" -> currentDir = root
                line.startsWith("\$ cd ") -> {
                    val newDir = Dir(line.drop(5), currentDir)
                    currentDir.subDirs += newDir
                    currentDir = newDir
                }

                line == "\$ ls" -> {}
                line.startsWith("dir ") -> {}
                else -> {
                    val size = line.takeWhile { it.isDigit() }.toInt()
                    currentDir.addFileSize(size)
                }
            }
        }

        suspend fun SequenceScope<Dir>.iterate(dir: Dir) {
            yield(dir)
            dir.subDirs.forEach { iterate(it) }
        }
        val allDirs = sequence { iterate(root) }
        return allDirs.filter { it.filesSize <= 100000 }.sumOf { it.filesSize }
    }

    fun part2(input: List<String>): Long {
        val root = Dir("/", null)
        var currentDir = root
        input.forEach { line ->
            when {
                line == "\$ cd .." -> currentDir = currentDir.parent!!
                line == "\$ cd /" -> currentDir = root
                line.startsWith("\$ cd ") -> {
                    val newDir = Dir(line.drop(5), currentDir)
                    currentDir.subDirs += newDir
                    currentDir = newDir
                }

                line == "\$ ls" -> {}
                line.startsWith("dir ") -> {}
                else -> {
                    val size = line.takeWhile { it.isDigit() }.toInt()
                    currentDir.addFileSize(size)
                }
            }
        }

        val total = 70000000
        val requiredFree = 30000000
        val currentFree = total - root.filesSize
        val deleteAtLeast = requiredFree - currentFree
        val allDirs = generateSequence(listOf(root)) { dirs ->
            dirs.flatMap { it.subDirs }.takeIf { it.isNotEmpty() }
        }.flatten()
        return allDirs.filter { it.filesSize >= deleteAtLeast }.minBy { it.filesSize }.filesSize
    }
    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}

data class Dir(
    val name: String,
    val parent: Dir?,
    var filesSize: Long = 0,
    val subDirs: MutableList<Dir> = ArrayList()
) {
    fun addFileSize(value: Int) {
        filesSize += value
        parent?.addFileSize(value)
    }

    override fun toString(): String = "[$name $filesSize]"
}

fun printDirTree(dir: Dir, indent: Int = 0) {
    println(" ".repeat(indent) + "${dir.name} ${dir.filesSize}")
    dir.subDirs.forEach { printDirTree(it, indent + 4) }
}

