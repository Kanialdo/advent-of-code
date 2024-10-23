package day07

import utils.verify
import java.io.File
import kotlin.math.abs

/** https://adventofcode.com/2021/day/7 */

fun main() {

    fun File.read() = readLines().first().split(",").map(String::toInt)

    fun calculateCost(numbers: List<Int>, pathCostCalculator: (Long) -> Long): Long {
        val min = numbers.minOf { it }
        val max = numbers.maxOf { it }

        val sums = LongArray(max - min + 1)

        for (i in min..max) {
            val id = i - min
            sums[id] = numbers.sumOf { pathCostCalculator(abs(i - it).toLong()) }
        }
        return sums.minOf { it }
    }

    fun part1(numbers: List<Int>) = calculateCost(numbers) { it }

    fun part2(numbers: List<Int>) = calculateCost(numbers) { (it * (it + 1)) / 2 }

    // ---- TEST

    val testNumbers = File("src/day07/input_test.txt").read()

    verify(37, part1(testNumbers))
    verify(168, part2(testNumbers))

    // ---- RUN

    val numbers = File("src/day07/input.txt").read()

    verify(349357, part1(numbers))
    verify(96708205, part2(numbers))
}