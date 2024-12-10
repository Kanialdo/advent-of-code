// https://adventofcode.com/2024/day/10

fun main() {

    data class Point(val x: Int, val y: Int) {
        override fun toString(): String {
            return "[$x, $y]"
        }
    }

    data class Array2d(val xSize: Int, val ySize: Int) {

        val array = Array(ySize) { Array(xSize) { 0 } }

        operator fun get(x: Int, y: Int) = if (isInBounds(x, y)) array[y][x] else null
        operator fun set(x: Int, y: Int, value: Int) {
            array[y][x] = value
        }

        fun isInBounds(x: Int, y: Int) = x in 0 until xSize && y in 0 until ySize

        fun getPositions(value: Int) =
            array.mapIndexed { y, list -> list.mapIndexed { x, v -> if (v == value) Point(x, y) else null } }.flatten()
                .filterNotNull()

        fun printArray() {
            for (x in array.indices) {
                for (y in array[x].indices) {
                    print(array[x][y])
                }
                println("")
            }
            println("")
        }
    }

    fun goDown(array: Array2d, path: List<Point>, expectedHeight: Int, x: Int, y: Int): List<List<Point>?> {
        val path = path + Point(x, y)
        return listOf<List<Point>?>() + when {
            array[x, y] != expectedHeight -> listOf(null)
            array[x, y] == 0 -> listOf(path)
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

    fun goUp(array: Array2d, path: List<Point>, expectedHeight: Int, x: Int, y: Int): List<List<Point>?> {
        val path = path + Point(x, y)
        return listOf<List<Point>?>() + when {
            array[x, y] != expectedHeight -> listOf(null)
            array[x, y] == 9 -> listOf(path)
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

        val array = Array2d(input[0].length, input.size)

        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                array[x, y] = char.digitToInt()
            }
        }

        return array.getPositions(9)
            .sumOf {
                goDown(array, listOf(it), 9, it.x, it.y)
                    .filterNotNull()
                    .groupBy { it.last() }
                    .count()
            }
    }


    fun part2(input: List<String>): Int {
        val array = Array2d(input[0].length, input.size)

        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                array[x, y] = char.digitToInt()
            }
        }

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
