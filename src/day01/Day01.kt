package day01

import java.io.File

/** https://adventofcode.com/2021/day/1 */

object Day01 {

    fun part1(numbers: List<Int>): Int {
        var result = 0
        var lastNumber = 0
        numbers.forEachIndexed { id, value ->
            if (id != 0 && value > lastNumber) {
                result++
            }
            lastNumber = value
        }
        return result
    }

    fun part2(numbers: List<Int>): Int {
        var result = 0
        var lastSum = numbers[0] + numbers[1] + numbers[2]
        var currentSum = 0

        for (i in 3 until numbers.size) {
            currentSum = lastSum - numbers[i - 3] + numbers[i]
            if (currentSum > lastSum) {
                result++
            }
            lastSum = currentSum
        }

        return result
    }
}

fun main() {

    val numbers = File("src/day01/input.txt").readLines().map(String::toInt)

    println("Result - part1: ${Day01.part1(numbers)}")
    println("Result - part2: ${Day01.part2(numbers)}")
}