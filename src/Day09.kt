import java.lang.IllegalArgumentException
import kotlin.math.absoluteValue
import kotlin.math.sign

fun main() {
    fun part1(input: List<String>): Int {
        val visited = HashSet<Long>()
        val rope = List(2) { XY(0, 0) }
        input.forEach { line ->
            val dir = line[0]
            val steps = line.drop(2).toInt()
            repeat(steps) {
                moveRope(rope, dir)
                visited += rope.last().encode()
            }
        }
        return visited.size
    }

    fun part2(input: List<String>): Int {
        val visited = HashSet<Long>()
        val rope = List(10) { XY(0, 0) }
        input.forEach { line ->
            val dir = line[0]
            val steps = line.drop(2).toInt()
            repeat(steps) {
                moveRope(rope, dir)
                visited += rope.last().encode()
            }
        }
        return visited.size
    }

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}

fun moveRope(rope: List<XY>, dir: Char) {
    val head = rope.first()
    when (dir) {
        'U' -> head.y -= 1
        'D' -> head.y += 1
        'L' -> head.x -= 1
        'R' -> head.x += 1
        else -> throw IllegalArgumentException("Unknown dir: $dir")
    }
    for (i in 1..rope.lastIndex) {
        val prev = rope[i - 1]
        val current = rope[i]
        val diffX = prev.x - current.x
        val diffY = prev.y - current.y
        if (diffX.absoluteValue >= 2 || diffY.absoluteValue >= 2) {
            current.x += diffX.sign
            current.y += diffY.sign
        }
    }
}

fun printRope(rope: List<XY>) {
    val map = HashMap<XY, Int>()
    rope.forEachIndexed { i, xy -> map[xy] = i }
    for (y in 0..4) {
        for (x in 0..5) {
            val s = map[XY(x, y)]?.toString() ?: "."
            print(s)
        }
        println()
    }
    println()
}

data class XY(var x: Int, var y: Int)  {
    fun encode(): Long = (x.toLong() shl 32) + y
}
