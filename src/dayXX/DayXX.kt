package dayXX

import utils.verify
import java.io.File

/** https://adventofcode.com/2021/day/TODO */

fun main() {

    fun part1(numbers: List<String>): Int {
        return TODO()
    }


    fun part2(numbers: List<String>): Int {
        return TODO()
    }

    // ---- TEST

    val testNumbers = File("src/dayXX/input_test.txt").readLines()

    verify(TODO(), part1(testNumbers))
    verify(TODO(), part2(testNumbers))

    // ---- RUN

    val numbers = File("src/dayXX/input.txt").readLines()

    verify(TODO(), part1(numbers))
    verify(TODO(), part2(numbers))
}