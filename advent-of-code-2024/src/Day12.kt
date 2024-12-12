import util.Array2d
import util.Point
import util.Vector

// https://adventofcode.com/2024/day/12

fun main() {

    fun part1(input: List<String>): Int {

        val array2d = Array2d.readChar(input)
        val mask = array2d.createMask()

        val vectors = Vector.DIRECTIONS_4

        var sum = 0

        array2d.forEach { x, y, value ->
            if (!mask[x, y]) {
                var fence = 0
                val toVisit = mutableSetOf(Point(x, y))
                val visited = mutableSetOf<Point>()

                while (toVisit.isNotEmpty()) {
                    val visiting = toVisit.first()
                    for (vector in vectors) {
                        val next = visiting + vector
                        when {
                            next in visited -> Unit
                            array2d.getSafe(next) == value -> toVisit += next
                            else -> fence++
                        }
                    }
                    toVisit.remove(visiting)
                    visited += visiting
                }
                visited.forEach { mask[it] = true }
                sum += fence * visited.size
            }
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        return TODO()
    }

    val testInput = readInput("Day12_test")
    val input = readInput("Day12")

    test(1930) { part1(testInput) }
    exec { part1(input) }
    test(1206) { part2(testInput) }
    exec { part2(input) }
}
