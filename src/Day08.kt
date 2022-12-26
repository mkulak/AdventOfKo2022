fun main() {
    fun part1(input: List<String>): Int {
        val originalData = input.map { line -> line.map { it.code - '0'.code }.toIntArray() }
        val visible = HashSet<Int>()
        val width = originalData[0].size
        val height = originalData.size
        var data = originalData.map { it.clone() }
        fun check(i: Int, j: Int, di: Int, dj: Int) {
            val prev = data[i + di][j + dj]
            if (data[i][j] > prev) {
                visible += i * width + j
            } else {
                data[i][j] = prev
            }
        }
        (1..(height - 2)).forEach { i ->
            (1..(width - 2)).forEach { j ->
                check(i, j, -1, 0)
            }
        }
        data = originalData.map { it.clone() }
        (1..(width - 2)).forEach { j ->
            (1..(height - 2)).forEach { i ->
                check(i, j, 0, -1)
            }
        }
        data = originalData.map { it.clone() }
        ((height - 2) downTo 1).forEach { i ->
            (1..(width - 2)).forEach { j ->
                check(i, j, 1, 0)
            }
        }
        data = originalData.map { it.clone() }
        ((width - 2) downTo 1).forEach { j ->
            (1..(height - 2)).forEach { i ->
                check(i, j, 0, 1)
            }
        }
        return visible.size + height * 2 + width * 2 - 4
    }

    fun part2(input: List<String>): Long {
        val originalData = input.map { line -> line.map { it.code - '0'.code }.toIntArray() }
        val width = originalData[0].size
        val height = originalData.size
        fun getScenicScore(i: Int, j: Int): Long {
            var upCount = 0L
            for (y in (i - 1) downTo 0) {
                upCount++
                if (originalData[y][j] >= originalData[i][j]) break
            }
            var downCount = 0L
            for (y in (i + 1)..(height - 1)) {
                downCount++
                if (originalData[y][j] >= originalData[i][j]) break
            }
            var leftCount = 0L
            for (x in (j - 1) downTo 0) {
                leftCount++
                if (originalData[i][x] >= originalData[i][j]) break
            }
            var rightCount = 0L
            for (x in (j + 1)..(width - 1)) {
                rightCount++
                if (originalData[i][x] >= originalData[i][j]) break
            }
            return upCount * downCount * leftCount * rightCount
        }

        return (1..(height - 2)).flatMap { i -> (1..(width - 2)).map { j -> i to j } }
            .maxOf { (i, j) -> getScenicScore(i, j) }
    }
    val input = readInput("Day08")
    println(part1(input)) // 1533
    println(part2(input)) // 345744
}
