import java.lang.IllegalArgumentException
import kotlin.math.absoluteValue
import kotlin.math.sign

fun main() {
    fun part1(input: List<String>): Int {
        val visited = HashSet<Long>()
        val head = XY(0, 0)
        val tail = XY(0, 0)
        input.forEach { line ->
            val steps = line.drop(2).toInt()
            repeat(steps) {
                when (line[0]) {
                    'U' -> head.y -= 1
                    'D' -> head.y += 1
                    'L' -> head.x -= 1
                    'R' -> head.x += 1
                    else -> throw IllegalArgumentException("Unknown dir: ${line[0]}")
                }
                val diffX = head.x - tail.x
                val diffY = head.y - tail.y
                if (diffX.absoluteValue >= 2) {
                    if (diffY.absoluteValue >= 1) {
                        tail.y += diffY.sign
                    }
                    tail.x += diffX.sign
                }
                if (diffY.absoluteValue >= 2) {
                    if (diffX.absoluteValue >= 1) {
                        tail.x += diffX.sign
                    }
                    tail.y += diffY.sign
                }
                visited += tail.encode()
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
            val inc = when (dir) {
                'U' -> XY(0, -1)
                'D' -> XY(0, 1)
                'L' -> XY(-1, 0)
                'R' -> XY(1, 0)
                else -> throw IllegalArgumentException("Unknown dir: $dir")
            }
            repeat(steps) {
                moveRope(rope, inc)
                visited += rope.last().encode()
            }
        }
        return visited.size
    }

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}

fun moveRope(rope: List<XY>, inc: XY) {
    rope[0] += inc
    for (i in 1..rope.lastIndex) {
        val prev = rope[i - 1]
        val current = rope[i]
        val diffX = prev.x - current.x
        val diffY = prev.y - current.y
        current.x += diffX.sign
        current.y += diffY.sign
    }
}


data class XY(var x: Int, var y: Int)  {
    fun encode(): Long = (x.toLong() shl 32) + y

    operator fun plusAssign(other: XY) {
        x += other.x
        y += other.y
    }

}
