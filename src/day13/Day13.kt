package day13

import utils.verify
import java.io.File

/** https://adventofcode.com/2021/day/13 */

fun main() {

    data class Fold(val x: Boolean, val pos: Int)

    class Data(lines: List<String>) {
        private val matrix: Array<BooleanArray>
        private val folds: ArrayDeque<Fold>

        private var maxX: Int
        private var maxY: Int

        init {
            val _folds: MutableList<Fold> = mutableListOf()
            val list: MutableList<Pair<Int, Int>> = mutableListOf()
            lines.forEach { line ->
                when {
                    line.startsWith("fold") -> line.replace("fold along ", "").split("=")
                        .let { _folds.add(Fold(it[0] == "x", it[1].toInt())) }
                    line.isBlank() -> {}
                    else -> line.split(",").map(String::toInt).let { list.add(it[0] to it[1]) }
                }
            }
            folds = ArrayDeque(_folds)
            maxX = list.maxOf { it.first + 1 }
            maxY = list.maxOf { it.second + 1 }
            matrix = Array(maxY) { BooleanArray(maxX) }
            list.forEach { matrix[it.second][it.first] = true }
        }

        fun fold(limit: Int = Int.MAX_VALUE) {
            var counter = 0
            do {
                val fold = folds.removeFirstOrNull() ?: return
                if (fold.x) {
                    for (x in 0 until fold.pos) {
                        for (y in 0 until maxY) {
                            matrix[y][x] = matrix[y][x] || matrix[y][maxX - x - 1]
                        }
                    }
                    maxX = fold.pos
                } else {
                    for (x in 0 until maxX) {
                        for (y in 0 until fold.pos) {
                            matrix[y][x] = matrix[y][x] || matrix[maxY - y - 1][x]
                        }
                    }
                    maxY = fold.pos
                }
                counter++
                if (counter == limit) {
                    return
                }
            } while (true)
        }

        fun print() {
            println()
            for (y in 0 until maxY) {
                for (x in 0 until maxX) {
                    print(if (matrix[y][x]) "#" else ".")
                }
                println()
            }
        }

        fun count(): Int {
            var result = 0
            for (y in 0 until maxY) {
                for (x in 0 until maxX) {
                    if (matrix[y][x]) {
                        result++
                    }
                }
            }
            return result
        }
    }

    fun File.read() = Data(readLines())

    fun part1(data: Data): Int {
        // data.print()
        data.fold(1)
        // data.print()
        return data.count()
    }


    fun part2(data: Data): Int {
        data.fold()
        data.print()
        return 0
    }

    // ---- RUN

    val testData = File("src/day13/input_test.txt").read()
    val data = File("src/day13/input.txt").read()

    verify(17, part1(testData))
    verify(647, part1(data))

    verify(0, part2(testData)) // result 0
    verify(0, part2(data)) // result HEJHJRCJ
}