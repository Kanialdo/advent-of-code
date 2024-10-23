package day09

import utils.verify
import java.io.File

/** https://adventofcode.com/2021/day/9 */

fun main() {

    class Matrix {

        private lateinit var data: Array<IntArray>

        constructor(x: Int, y: Int) {
            data = Array(y) { IntArray(x) }
        }

        constructor (input: List<String>) {
            data = Array(input.size) { pos -> input[pos].toList().map(Char::digitToInt).toIntArray() }
        }

        val sizeX get() = data.first().size
        val sizeY get() = data.size

        fun field(x: Int, y: Int) = data[y][x]
        fun safeField(x: Int, y: Int) = if (x >= 0 && y >= 0 && x < sizeX && y < sizeY) data[y][x] else 9
        fun set(x: Int, y: Int, value: Int) {
            if (x >= 0 && y >= 0 && x < sizeX && y < sizeY) data[y][x] = value
        }

        fun count(value: Int) = data.sumOf { it.count { it == value } }
    }

    fun File.read(): Matrix = Matrix(readLines())

    fun part1(matrix: Matrix): Int {
        val items: MutableList<Int> = mutableListOf()
        for (x in 0 until matrix.sizeX) {
            for (y in 0 until matrix.sizeY) {
                if (matrix.safeField(x, y) < matrix.safeField(x - 1, y)
                    && matrix.safeField(x, y) < matrix.safeField(x + 1, y)
                    && matrix.safeField(x, y) < matrix.safeField(x, y - 1)
                    && matrix.safeField(x, y) < matrix.safeField(x, y + 1)
                ) {
                    items.add(matrix.field(x, y))
                }
            }
        }
        return items.sum() + items.count()
    }

    fun sonar(x: Int, y: Int, matrix: Matrix, shadowMap: Matrix, areaId: Int) {

        val points: ArrayDeque<Pair<Int, Int>> = ArrayDeque()
        points.add(x to y)

        while (points.isNotEmpty()) {
            val (x1, y1) = points.removeLast()
            shadowMap.set(x1, y1, areaId)
            if (shadowMap.safeField(x1 - 1, y1) == 0 && matrix.safeField(x1 - 1, y1) != 9) {
                points.add(x1 - 1 to y1)
            }
            if (shadowMap.safeField(x1 + 1, y1) == 0 && matrix.safeField(x1 + 1, y1) != 9) {
                points.add(x1 + 1 to y1)
            }
            if (shadowMap.safeField(x1, y1 - 1) == 0 && matrix.safeField(x1, y1 - 1) != 9) {
                points.add(x1 to y1 - 1)
            }
            if (shadowMap.safeField(x1, y1 + 1) == 0 && matrix.safeField(x1, y1 + 1) != 9) {
                points.add(x1 to y1 + 1)
            }
        }
    }

    fun part2(matrix: Matrix): Int {

        val shadowMap = Matrix(matrix.sizeX, matrix.sizeY)
        var areaId = 0

        for (x in 0 until matrix.sizeX) {
            for (y in 0 until matrix.sizeY) {
                if (matrix.field(x, y) != 9 && shadowMap.field(x, y) == 0) {
                    areaId++
                    sonar(x, y, matrix, shadowMap, areaId)
                }
            }
        }

        val basins: MutableMap<Int, Int> = mutableMapOf()
        for (i in 1..areaId) {
            basins[i] = shadowMap.count(i)
        }

        return basins.values.sorted().reversed().subList(0, 3).fold(1) { acc, v -> acc * v }
    }

    // ---- TEST

    val testMatrix = File("src/day09/input_test.txt").read()

    verify(15, part1(testMatrix))
    verify(1134, part2(testMatrix))

    // ---- RUN

    val matrix = File("src/day09/input.txt").read()

    verify(564, part1(matrix))
    verify(1038240, part2(matrix))
}