import java.lang.IllegalArgumentException
import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        val visited = HashSet<Long>()
        var headX = 0
        var headY = 0
        var tailX = 0
        var tailY = 0
        input.forEach { line ->
            val dir = line[0]
            val steps = line.drop(2).toInt()
            val (incX, incY) = when (dir) {
                'U' -> 0 to -1
                'D' -> 0 to 1
                'L' -> -1 to 0
                'R' -> 1 to 0
                else -> throw IllegalArgumentException("Unknown dir: $dir")
            }
            repeat(steps) {
                headX += incX
                headY += incY
                val diffX = headX - tailX
                val diffY = headY - tailY
                when {
                    diffX.absoluteValue > 1 -> {
                        if (diffY.absoluteValue > 0) {
                            tailY = headY
                        }
                        tailX += incX
                    }
                    diffY.absoluteValue > 1 -> {
                        if (diffX.absoluteValue > 0) {
                            tailX = headX
                        }
                        tailY += incY
                    }
                }
                visited += encode(tailX, tailY)
            }
        }
        return visited.size
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}

fun encode(x: Int, y: Int): Long = (x.toLong() shl 32) + y
