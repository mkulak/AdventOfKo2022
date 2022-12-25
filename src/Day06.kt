fun main() {
    fun part1(input: List<String>): Int {
        input.first().foldIndexed(ArrayDeque<Char>()) { i, acc, ch ->
            acc += ch
            if (acc.size > 4) acc.removeFirst()
            if (acc.toSet().size == 4) return i + 1
            acc
        }
        return -1
    }

    fun part2(input: List<String>): Int {
        input.first().foldIndexed(ArrayDeque<Char>()) { i, acc, ch ->
            acc += ch
            if (acc.size > 14) acc.removeFirst()
            if (acc.toSet().size == 14) return i + 1
            acc
        }
        return -1
    }

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
