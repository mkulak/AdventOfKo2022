fun main() {
    fun part1(input: List<String>): Int {
        var cycle = 1
        var register = 1
        var res = 0
        fun checkCycle() {
            if (cycle in setOf(20, 60, 100, 140, 180, 220)) {
                res += cycle * register
            }
        }
        input.forEach { line ->
            val inc = if (line != "noop") {
                cycle++
                checkCycle()
                line.drop(5).toInt()
            } else 0
            cycle++
            register += inc
            checkCycle()
        }
        return res
    }

    fun part2(input: List<String>): Int {
        val width = 40
        val height = 6
        val screen = BooleanArray(width * height)
        var register = 1
        var cycle = 0
        fun checkCycle() {
            if ((cycle % width) in (register - 1)..(register + 1) ) {
                screen[cycle] = true
            }
        }
        input.forEach { line ->
            val inc = if (line != "noop") {
                cycle++
                checkCycle()
                line.drop(5).toInt()
            } else 0
            cycle++
            register += inc
            checkCycle()
        }
        screen.toList().chunked(width).forEach { line ->
            println(line.joinToString("") { if (it) "█" else " " })
        }
        return 0
    }

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}

