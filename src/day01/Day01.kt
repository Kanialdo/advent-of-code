package day01

import java.io.File

/** https://adventofcode.com/2021/day/1 */

object Day01 {

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
}

fun main() {

    val numbers = File("src/day01/input.txt").readLines().map(String::toInt)

    println("Result - part1: ${Day01.part1(numbers)}")
    println("Result - part2: ${Day01.part2(numbers)}")
}