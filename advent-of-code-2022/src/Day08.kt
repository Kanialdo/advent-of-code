import kotlin.math.max

fun main() {

    fun part1(input: List<String>): Int {
        val area = input.map { row -> row.toCharArray().map { it.digitToInt() } }
        val visibility = Array(area.size) { BooleanArray(area.first().size) { false } }

        val dimension = area.size

        for (y in 1 until dimension - 1) {
            var max = area[y].first()
            for (x in 1 until dimension - 1) {
                visibility[y][x] = visibility[y][x] || area[y][x] > max
                max = max(area[y][x], max)
            }
            max = area[y].last()
            for (x in dimension - 2 downTo 1) {
                visibility[y][x] = visibility[y][x] || area[y][x] > max
                max = max(area[y][x], max)
            }
        }
        for (x in 1 until dimension - 1) {
            var max = area.first()[x]
            for (y in 1 until dimension - 1) {
                visibility[y][x] = visibility[y][x] || area[y][x] > max
                max = max(area[y][x], max)
            }
            max = area.last()[x]
            for (y in dimension - 2 downTo 1) {
                visibility[y][x] = visibility[y][x] || area[y][x] > max
                max = max(area[y][x], max)
            }
        }

        // for (y in area.first().indices) {
        //     for (x in area.indices) {
        //         print(if (visibility[y][x]) "o" else "x")
        //     }
        //     println()
        // }

        return visibility.sumOf { it.count { it } } + visibility.size * 4 - 4
    }

    fun List<List<Int>>.countRight(x: Int, y: Int): Int {
        var count = 0
        for (i in x + 1 until size) {
            count++
            if (this[y][x] <= this[y][i]) break
        }
        return count
    }

    fun List<List<Int>>.countLeft(x: Int, y: Int): Int {
        var count = 0
        for (i in x - 1 downTo 0) {
            count++
            if (this[y][x] <= this[y][i]) break
        }
        return count
    }

    fun List<List<Int>>.countTop(x: Int, y: Int): Int {
        var count = 0
        for (i in y + 1 until size) {
            count++
            if (this[y][x] <= this[i][x]) break
        }
        return count
    }

    fun List<List<Int>>.countBottom(x: Int, y: Int): Int {
        var count = 0
        for (i in y - 1 downTo 0) {
            count++
            if (this[y][x] <= this[i][x]) break
        }
        return count
    }

    fun part2(input: List<String>): Int {
        val area = input.map { row -> row.toCharArray().map { it.digitToInt() } }
        val score = Array(area.size) { IntArray(area.first().size) { 0 } }

        val dimension = area.size

        for (y in 1 until  dimension - 1 ) {
            for (x in 1 until  dimension - 1) {
                score[y][x] = area.countRight(x = x, y = y) *
                        area.countLeft(x = x, y = y) *
                        area.countBottom(x = x, y = y) *
                        area.countTop(x = x, y = y)
            }
        }

        // for (y in 0 until dimension) {
        //     for (x in 0 until dimension) {
        //         print(score[y][x])
        //     }
        //     println()
        // }

        return score.maxOf { it.max() }
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput), 21)
    check(part2(testInput), 8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}