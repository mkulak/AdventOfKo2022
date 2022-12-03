fun main() {
    fun part1(input: List<String>): Int =
        input.sumOf { line ->
            val first = line.take(line.length / 2).toSet()
            val second = line.drop(line.length / 2).toSet()
            first.intersect(second).sumOf { if (it > 'Z') it.code - 'a'.code + 1 else it.code - 'A'.code + 27 }
        }

    fun part2(input: List<String>): Int =
        input.windowed(3, 3).sumOf { (a, b, c) ->
            val common = a.toSet().intersect(b.toSet()).intersect(c.toSet())
            common.sumOf { if (it > 'Z') it.code - 'a'.code + 1 else it.code - 'A'.code + 27 }
        }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
