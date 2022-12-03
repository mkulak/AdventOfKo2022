fun main() {
    fun part1(input: List<String>): Int =
        input.sumOf { line ->
            val their = line[0].code - 'A'.code
            val mine = line[2].code - 'X'.code
            when (their - mine) {
                0 -> 3
                -1, 2 -> 6
                else -> 0
            } + mine + 1
        }

    fun part2(input: List<String>): Int =
        input.sumOf { line ->
            val their = line[0].code - 'A'.code
            val result = line[2].code - 'X'.code
            when (result) {
                0 -> (their + 2) % 3
                1 -> 3 + their
                else -> 6 + (their + 1) % 3
            } + 1
        }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
