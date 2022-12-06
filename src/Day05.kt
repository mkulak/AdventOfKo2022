fun main() {
    val regex = "move ([0-9]+) from ([0-9]+) to ([0-9]+)".toRegex()

    fun part1(input: List<String>): String {
        val part1 = input.takeWhile { it.startsWith("[") || it.startsWith("  ") }
        val part2 = input.drop(part1.size + 2)
        val stacks = List((input[part1.size].length + 2) / 4)  { ArrayDeque<Char>() }
        part1.forEach { line ->
            line.windowed(4, 4, partialWindows = true).forEachIndexed { index, str ->
                if (str[1] != ' ') stacks[index] += str[1]
            }
        }
        part2.forEach { line ->
            val (count, from, to) = regex.matchEntire(line)!!.groupValues.drop(1).map { it.toInt() }
            repeat(count) {
                val move = stacks[from - 1].removeFirst()
                stacks[to - 1].addFirst(move)
            }
        }
        return stacks.joinToString("") { it.first().toString() }
    }


    fun part2(input: List<String>): String {
        val part1 = input.takeWhile { it.startsWith("[") || it.startsWith("  ") }
        val part2 = input.drop(part1.size + 2)
        val stacks = List((input[part1.size].length + 2) / 4)  { ArrayDeque<Char>() }
        part1.forEach { line ->
            line.windowed(4, 4, partialWindows = true).forEachIndexed { index, str ->
                if (str[1] != ' ') stacks[index] += str[1]
            }
        }
        val temp = ArrayDeque<Char>()
        part2.forEach { line ->
            val (count, from, to) = regex.matchEntire(line)!!.groupValues.drop(1).map { it.toInt() }
            repeat(count) {
                val move = stacks[from - 1].removeFirst()
                temp.addFirst(move)
            }
            repeat(count) {
                stacks[to - 1].addFirst(temp.removeFirst())
            }
        }
        return stacks.joinToString("") { it.first().toString() }
    }

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
