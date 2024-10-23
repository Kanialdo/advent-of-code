package day06

import utils.verify
import java.io.File

/** https://adventofcode.com/2021/day/6 */

fun main() {

    class Environment(fishes: List<Int>) {
        private val generation = LongArray(9)

        init {
            for (i in 1 until 9) {
                generation[i] = fishes.count { it == i }.toLong()
            }
        }

        fun simulate(days: Int) {
            for (day in 0 until days) {
                val threshold = generation[0]
                for (i in 0 until generation.size - 1) {
                    generation[i] = generation[i + 1]
                }
                generation[6] += threshold
                generation[8] = threshold
            }
        }

        fun sum() = generation.sum()
    }

    fun File.read() = readLines().first().split(",").map(String::toInt)

    fun part1(fishes: List<Int>): Long {
        val environment = Environment(fishes)
        environment.simulate(80)
        return environment.sum()
    }

    fun part2(fishes: List<Int>): Long {
        val environment = Environment(fishes)
        environment.simulate(256)
        return environment.sum()
    }

    // ---- TEST

    val testFishes = File("src/day06/input_test.txt").read()

    verify(5934, part1(testFishes))
    verify(26984457539, part2(testFishes))

    // ---- RUN

    val fishes = File("src/day06/input.txt").read()

    verify(353274, part1(fishes))
    verify(1609314870967, part2(fishes))
}