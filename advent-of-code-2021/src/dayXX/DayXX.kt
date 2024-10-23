package dayXX

import utils.verify
import java.io.File

/** https://adventofcode.com/2021/day/XX */

fun main() {

    val rootDir = File("src/dayXX/")

    fun part1(data: List<String>): Int {
        return -1
    }


    fun part2(data: List<String>): Int {
        return -1
    }

    // ---- RUN

    val testData = File(rootDir, "input_test.txt").readLines()
    val data = File(rootDir, "input.txt").readLines()

    verify(0, part1(testData))
    verify(0, part1(data))

    verify(0, part2(testData))
    verify(0, part2(data))
}