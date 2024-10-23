package day11

import utils.verify
import java.io.File

/** https://adventofcode.com/2021/day/11 */

fun main() {

    class Matrix(data: List<String>) {
        private val _data: Array<IntArray> = data.map { it.map { it.digitToInt() }.toIntArray() }.toTypedArray()

        fun get(x: Int, y: Int) = _data[y][x]

        fun set(x: Int, y: Int, value: Int) {
            _data[y][x] = value
        }

        fun safeIncrease(x: Int, y: Int) {
            if ((x in (0..9)) && (y in 0..9)) {
                set(x, y, get(x, y) + 1)
            }
        }

        fun safeArealIncrease(x: Int, y: Int) {
            for (x1 in -1..1) {
                for (y1 in -1..1) {
                    safeIncrease(x + x1, y + y1)
                }
            }
        }

        fun getNextFlashingPos(): Pair<Int, Int>? {
            for (y in 0 until 10) {
                for (x in 0 until 10) {
                    if (get(x, y) > 9) {
                        return x to y
                    }
                }
            }
            return null
        }

        fun add1() {
            for (y in 0 until 10) {
                for (x in 0 until 10) {
                    safeIncrease(x, y)
                }
            }
        }

        fun resetNegatives() {
            for (y in 0 until 10) {
                for (x in 0 until 10) {
                    if (get(x, y) < 0) {
                        set(x, y, 0)
                    }
                }
            }
        }

        fun count(predicate: (Int) -> Boolean) = _data.sumOf { it.count(predicate) }

        fun print() {
            println("----------")
            _data.forEach { println(it.joinToString(separator = "")) }
        }
    }

    fun File.read(): Matrix = Matrix(readLines())

    fun part1(data: Matrix): Int {
        var result = 0
        for (i in 0 until 100) {
            data.add1()
            var next: Pair<Int, Int>?
            do {
                next = data.getNextFlashingPos()
                if (next == null) {
                    break
                }
                data.safeArealIncrease(next.first, next.second)
                data.set(next.first, next.second, Int.MIN_VALUE)
            } while (true)
            result += data.count { it < 0 }
            data.resetNegatives()
        }
        return result
    }

    fun part2(data: Matrix): Int {
        for (i in 0 until 500) {
            data.add1()
            lateinit var next: Pair<Int, Int>
            do {
                next = data.getNextFlashingPos() ?: break
                data.safeArealIncrease(next.first, next.second)
                data.set(next.first, next.second, Int.MIN_VALUE)
            } while (true)
            data.resetNegatives()
            if (data.count { it == 0 } == 100) {
                return i + 1
            }
        }
        return -1
    }

    // ---- RUN

    val testData = File("src/day11/input_test.txt").read()
    val data = File("src/day11/input.txt").read()

    verify(1656, part1(testData))
    verify(1655, part1(data))

    verify(95, part2(testData))
    verify(237, part2(data))

    // PS is in advent of code part2 thay expect 195, 337 ???
}