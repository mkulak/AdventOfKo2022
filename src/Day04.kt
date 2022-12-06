fun main() {
    val regex = "([0-9]+)-([0-9]+),([0-9]+)-([0-9]+)".toRegex()

    fun part1(input: List<String>): Int =
        input.count { line ->
            val (s1, e1, s2, e2) = regex.matchEntire(line)!!.groupValues.drop(1).map { it.toInt() }
            s1 <= s2 && e1 >= e2 || s1 >= s2 && e1 <= e2
        }

    fun part2(input: List<String>): Int =
        input.count { line ->
            val (s1, e1, s2, e2) = regex.matchEntire(line)!!.groupValues.drop(1).map { it.toInt() }
            s2 >= s1 && s2 <= e1
                || e2 >= s1 && e2 <= e1
                || s1 >= s2 && s1 <= e2
                || e1 >= s2 && e1 <= e2
        }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
