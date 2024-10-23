package day14

import utils.verify
import java.io.File

/** https://adventofcode.com/2021/day/14 */

fun main() {

    val rootDir = File("src/day14/")

    data class Insertion(val a: Char, val b: Char, val input: Char)
    data class Data(val polymer: String, val rules: List<Insertion>)

    fun Data.allPosibilities(): Set<Char> =
        (this.polymer.toCharArray() + rules.map { it.a.toString() + it.b + it.input }
            .joinToString(separator = "") { it }.toCharArray()).toSet()

    fun Data.polymerization(steps: Int): String {
        var polymer = this.polymer
        for (i in 0 until steps) {
            val array = CharArray(polymer.length * 2 - 1)
            polymer.windowed(2).forEachIndexed { pos, value ->
                array[pos * 2] = value[0]
                array[pos * 2 + 1] = this.rules.first { value[0] == it.a && value[1] == it.b }.input
            }
            array[array.size - 1] = polymer.last()
            polymer = array.concatToString()
        }
        return polymer
    }


    fun part1(data: Data): Int {
        val grouped = data.polymerization(10).groupBy { it }
        return grouped.maxOf { it.value.size } - grouped.minOf { it.value.size }
    }

    fun MutableMap<String, Long>.append(pair: String, times: Long = 1) {
        this[pair] = (this[pair] ?: 0) + times
    }

    fun MutableMap<String, Long>.drop(pair: String, times: Long = 1) {
        this[pair] = (this[pair] ?: 0) - times
    }

    fun part2(data: Data): Long {
        val counts: MutableMap<String, Long> = mutableMapOf()
        val countsPlus: MutableMap<String, Long> = mutableMapOf()

        data.polymer.windowed(2).forEach { counts.append(it) }

        // print("0: ")
        // counts.forEach { (key, value) -> print("$key: $value, ") }
        // println()

        for (i in 0 until 40) {
            counts.forEach { count ->
                data.rules.first { it.a == count.key[0] && it.b == count.key[1] }.let { insertion ->
                    countsPlus.append(insertion.a.toString() + insertion.input.toString(), count.value)
                    countsPlus.append(insertion.input.toString() + insertion.b.toString(), count.value)
                    countsPlus.drop(count.key, count.value)
                }
            }
            countsPlus.forEach { (key, value) -> counts[key] = (counts[key] ?: 0) + value }
            countsPlus.clear()
            counts.filter { it.value == 0L }.forEach { counts.remove(it.key) }
            // print("${i + 1}: ")
            // counts.forEach { (key, value) -> print("$key: $value, ") }
            // println()
        }

        val result: MutableMap<Char, Long> = mutableMapOf()
        data.allPosibilities().forEach {
            result[it] = 0
        }

        counts.map { it.key[0] to it.value }.forEach { result[it.first] = (result[it.first] ?: 0) + it.second }
        result[data.polymer.last()] = (result[data.polymer.last()] ?: 0) + 1

        return result.maxOf { it.value } - result.minOf { it.value }
    }

    // ---- RUN

    fun File.read(): Data {
        var polymer = ""
        val rules = mutableListOf<Insertion>()
        readLines().forEachIndexed { id, value ->
            when (id) {
                0 -> polymer = value
                1 -> {}
                else -> rules.add(Insertion(value[0], value[1], value[6]))
            }
        }
        rules.sortBy { it.a }
        return Data(polymer, rules)
    }

    val testData = File(rootDir, "input_test.txt").read()
    val data = File(rootDir, "input.txt").read()

    verify(1588, part1(testData))
    verify(3306, part1(data))

    verify(2188189693529, part2(testData))
    verify(3760312702877, part2(data))
}