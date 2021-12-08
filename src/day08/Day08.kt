package day08

import utils.verify
import java.io.File

/** https://adventofcode.com/2021/day/8 */

fun main() {

    class Line(val patterns: List<String>, val output: List<String>)

    //  000
    // 5   1
    //  666
    // 4   2
    //  333

    fun File.read(): List<Line> = readLines().map { line ->
        line.split(" ").let { Line(it.subList(0, it.size - 5), it.subList(it.size - 4, it.size)) }
    }

    fun String.toSignal(): Int {
        var signal = 0
        this.forEach {
            when (it) {
                'a' -> signal = signal or 0b01000000
                'b' -> signal = signal or 0b00100000
                'c' -> signal = signal or 0b00010000
                'd' -> signal = signal or 0b00001000
                'e' -> signal = signal or 0b00000100
                'f' -> signal = signal or 0b00000010
                'g' -> signal = signal or 0b00000001
            }
        }
        return signal
    }

    class Segment() {
        val segments = IntArray(7) { 0b01111111 }

        private fun mask(signal: Int, require: List<Int>, exclude: List<Int>) {
            require.forEach {
                segments[it] = segments[it] and signal
            }
            exclude.forEach {
                segments[it] = segments[it] and signal.inv()
            }
        }

        fun train(value: String) {
            val signal = value.toSignal()
            when (value.length) {
                2 -> mask(signal, listOf(1, 2), listOf(0, 3, 4, 5, 6))
                3 -> mask(signal, listOf(0, 1, 2), listOf(3, 4, 5, 6))
                4 -> mask(signal, listOf(1, 2, 5, 6), listOf(0, 3, 4))
                5 -> mask(signal, listOf(0, 3, 6), emptyList())
                6 -> mask(signal, listOf(0, 2, 3, 5), emptyList())
                7 -> mask(signal, listOf(0, 1, 2, 3, 4, 5, 6), emptyList())
            }
        }

        fun cleanup() {
            val toFix = segments.map { it.toString(2) }.filter { it.count { it == '1' } > 1 }.forEach {
                val second1 = it.indexOfLast { it == '1' }
                val secondPart = it.substring(second1)
                val fixedValue = if (segments.indexOf(secondPart.toInt(2)) != -1) {
                    it.replace('1', '0').substring(1, it.length).let { "1" + it }.toInt(2)
                } else {
                    it.replaceFirst('1', '0').toInt(2)
                }
                segments[segments.indexOf(it.toInt(2))] = fixedValue
            }
        }

        private fun buildDigit(positions: List<Int>) = positions.sumOf { position -> segments[position] }

        //  000
        // 5   1
        //  666
        // 4   2
        //  333

        fun decoder(): List<Int> = listOf(
            buildDigit(listOf(0, 1, 2, 3, 4, 5)),
            buildDigit(listOf(1, 2)),
            buildDigit(listOf(0, 1, 3, 4, 6)),
            buildDigit(listOf(0, 1, 2, 3, 6)),
            buildDigit(listOf(1, 2, 5, 6)),
            buildDigit(listOf(0, 2, 3, 5, 6)),
            buildDigit(listOf(0, 2, 3, 4, 5, 6)),
            buildDigit(listOf(0, 1, 2)),
            buildDigit(listOf(0, 1, 2, 3, 4, 5, 6)),
            buildDigit(listOf(0, 1, 2, 3, 5, 6)),
        )

        fun print() {
            println(segments.joinToString { it.toString(2) })
        }
    }

    fun part1(lines: List<Line>): Int {
        val segmentSize = listOf(2, 3, 4, 7)
        return lines.sumOf { it.output.filter { it.length in segmentSize }.count() }
    }

    fun part2(lines: List<Line>): Int {
        return lines.sumOf { line ->
            val segment = Segment()
            line.patterns.forEach { segment.train(it) }
            line.output.forEach { segment.train(it) }
            segment.cleanup()
            segment.print()
            val decoder = segment.decoder()
            line.output.map { decoder.indexOf(it.toSignal()) }.joinToString(separator = "").toInt()
        }
    }

    // ---- TEST

    val testLines = File("src/day08/input_test.txt").read()

    verify(26, part1(testLines))
    verify(61229, part2(testLines))

    // ---- RUN

    val lines = File("src/day08/input.txt").read()

    verify(330, part1(lines))
    verify(1010472, part2(lines))
}