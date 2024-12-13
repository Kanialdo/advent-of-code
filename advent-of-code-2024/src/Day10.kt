import util.Array2d
import util.Point

// https://adventofcode.com/2024/day/10

fun main() {

    fun goDown(array: Array2d<Int>, path: List<Point>, expectedHeight: Int, x: Int, y: Int): List<List<Point>?> {
        val path = path + Point(x, y)
        return listOf<List<Point>?>() + when {
            array.getSafe(x, y) != expectedHeight -> listOf(null)
            array.getSafe(x, y) == 0 -> listOf(path)
            else -> {
                var paths = listOf<List<Point>?>()
                paths = paths + goDown(array, path, expectedHeight - 1, x - 1, y)
                paths = paths + goDown(array, path, expectedHeight - 1, x, y - 1)
                paths = paths + goDown(array, path, expectedHeight - 1, x + 1, y)
                paths = paths + goDown(array, path, expectedHeight - 1, x, y + 1)
                paths
            }
        }
    }

    fun goUp(array: Array2d<Int>, path: List<Point>, expectedHeight: Int, x: Int, y: Int): List<List<Point>?> {
        val path = path + Point(x, y)
        return listOf<List<Point>?>() + when {
            array.getSafe(x, y) != expectedHeight -> listOf(null)
            array.getSafe(x, y) == 9 -> listOf(path)
            else -> {
                var paths = listOf<List<Point>?>()
                paths = paths + goUp(array, path, expectedHeight + 1, x - 1, y)
                paths = paths + goUp(array, path, expectedHeight + 1, x, y - 1)
                paths = paths + goUp(array, path, expectedHeight + 1, x + 1, y)
                paths = paths + goUp(array, path, expectedHeight + 1, x, y + 1)
                paths
            }
        }
    }

    fun part1(input: List<String>): Int {

        val array = Array2d.readNumbers(input)

        return array.getPositions(9)
            .sumOf {
                goDown(array, listOf(it), 9, it.x, it.y)
                    .filterNotNull()
                    .groupBy { it.last() }
                    .count()
            }
    }


    fun part2(input: List<String>): Int {

        val array = Array2d.readNumbers(input)

        return array.getPositions(0)
            .sumOf {
                goUp(array, listOf(it), 0, it.x, it.y)
                    .filterNotNull()
                    .toSet()
                    .count()
            }
    }

    val testInput = readInput("Day10_test")
    val input = readInput("Day10")

    test(36) { part1(testInput) }
    exec { part1(input) }
    test(81) { part2(testInput) }
    exec { part2(input) }
}
