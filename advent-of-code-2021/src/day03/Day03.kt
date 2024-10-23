package day03

import utils.verify
import java.io.File
import kotlin.math.pow

/** https://adventofcode.com/2021/day/3 */

fun main() {

    fun String.convertBytesToInt() = this.reversed().foldIndexed(0) { id, acc, v ->
        if (v == '1') {
            acc + 2.0.pow(id.toDouble()).toInt()
        } else {
            acc
        }
    }.toInt()

    fun part1(numbers: List<String>): Int {
        val data = IntArray(numbers.first().length)
        numbers.forEach { number ->
            number.forEachIndexed { id, v ->
                when (v) {
                    '0' -> data[id]--
                    '1' -> data[id]++
                    else -> throw Error()
                }
            }
        }
        val gamma = data.joinToString("") { if (it > 0) "1" else "0" }.convertBytesToInt()
        val epsilon = data.joinToString("") { if (it < 0) "1" else "0" }.convertBytesToInt()
        return gamma * epsilon
    }

    fun part2count(numbers: List<String>, flag: Boolean): Int {
        var temp = numbers
        for (i in numbers.indices) {
            val groups = temp.groupBy { it[i] }
            val g0 = groups['0'] ?: emptyList()
            val g1 = groups['1'] ?: emptyList()
            temp = when {
                g0.size > g1.size -> if (flag) g0 else g1
                g0.size < g1.size -> if (flag) g1 else g0
                else -> if (flag) g1 else g0
            }
            if (temp.size == 1) {
                return temp.first().convertBytesToInt()
            }
        }
        throw Error()
    }

    fun part2(numbers: List<String>): Int {

        val oxygen = part2count(numbers, true)
        val co2 = part2count(numbers, false)

        return oxygen * co2
    }

    // ---- TEST

    val testNumbers = File("src/day03/input_test.txt").readLines()

    verify(198, part1(testNumbers))
    verify(230, part2(testNumbers))

    // ---- RUN

    val numbers = File("src/day03/input.txt").readLines()

    verify(3687446, part1(numbers))
    verify(4406844, part2(numbers))
}