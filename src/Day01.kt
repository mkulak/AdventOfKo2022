fun main() {
    fun part1(input: List<String>): Int =
        input.fold(0 to 0) { (maximum, current), str ->
            if (str.isEmpty()) Math.max(maximum, current) to 0 else maximum to current + str.toInt()
        }.first

    fun part2(input: List<String>): Int =
        input.fold(listOf<Int>() to 0) { (list, acc), str ->
            if (str.isEmpty()) list + acc to 0 else list to acc + str.toInt()
        }.first.sortedBy { -it }.take(3).sum()

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
