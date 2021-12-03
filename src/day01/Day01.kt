package day01

import utils.verify
import java.io.File

/** https://adventofcode.com/2021/day/1 */

fun main() {

    fun part1(numbers: List<Int>): Int {
        var result = 0
        for (i in 1 until numbers.size) {
            if (numbers[i - 1] < numbers[i]) {
                result++
            }
        }
        return result
    }

    fun part1Alternative(numbers: List<Int>) = numbers.windowed(2).count { (a, b) -> a < b }

    fun part2(numbers: List<Int>): Int {
        var result = 0
        for (i in 3 until numbers.size) {
            if (numbers[i - 3] < numbers[i]) {
                result++
            }
        }
        return result
    }

    fun part2Alternative(numbers: List<Int>) = numbers.windowed(4).count { it[0] < it[3] }

    // ---- TEST

    val testNumbers = File("src/day01/input_test.txt").readLines().map(String::toInt)

    verify(7, part1(testNumbers))
    verify(7, part1Alternative(testNumbers))
    verify(5, part2(testNumbers))
    verify(5, part2Alternative(testNumbers))

    // ---- RUN

    val numbers = File("src/day01/input.txt").readLines().map(String::toInt)

    verify(1624, part1(numbers))
    verify(1653, part2(numbers))
}