package day05

import utils.verify
import java.io.File
import kotlin.math.abs

/** https://adventofcode.com/2021/day/5 */

fun main() {

    data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
        fun isHorizontalOrVertical() = x1 == x2 || y1 == y2
        fun is45() = abs(x2 - x1) == abs(y2 - y1)
    }

    class Map(lines: List<Line>) {
        private val width: Int = lines.maxOf { maxOf(it.x1, it.x2) }
        private val height: Int = lines.maxOf { maxOf(it.y1, it.y2) }
        private val map = Array(width + 1) { IntArray(height + 1) }

        fun markLines(lines: List<Line>) {
            lines.forEach { markLine(it) }
        }

        fun markLine(line: Line) {
            when {
                line.isHorizontalOrVertical() -> {
                    for (x in minOf(line.x1, line.x2)..maxOf(line.x1, line.x2)) {
                        for (y in minOf(line.y1, line.y2)..maxOf(line.y1, line.y2)) {
                            map[x][y] += 1
                        }
                    }
                }
                line.is45() -> {
                    val xStep = if (line.x1 < line.x2) 1 else -1
                    val yStep = if (line.y1 < line.y2) 1 else -1
                    for (i in 0 .. (abs(line.x1 - line.x2))) {
                        map[line.x1 + i * xStep][line.y1 + i * yStep] += 1
                    }
                }
            }
        }

        fun print() {
            println("MAP")
            println("------------------------")
            for (y in 0..height) {
                for (x in 0..width) {
                    when (map[x][y]) {
                        0 -> print(".")
                        else -> print(map[x][y])
                    }
                }
                println()
            }
            println("------------------------")
        }

        fun countOverlaps(): Int {
            var result = 0
            map.forEach { row -> row.forEach { if (it > 1) result++ } }
            return result
        }
    }

    fun File.read() = readLines().map { line ->
        line.replace(" -> ", ",").split(",").map(String::toInt).let {
            Line(it[0], it[1], it[2], it[3])
        }
    }

    fun part1(lines: List<Line>): Int {
        val _lines = lines.filter { it.isHorizontalOrVertical() }
        val map = Map(_lines)
        map.markLines(_lines)
        // map.print()
        return map.countOverlaps()
    }

    fun part2(lines: List<Line>): Int {
        val _lines = lines.filter { it.isHorizontalOrVertical() || it.is45() }
        val map = Map(_lines)
        map.markLines(_lines)
        // map.print()
        return map.countOverlaps()
    }

    // ---- TEST

    val testLines = File("src/day05/input_test.txt").read()

    verify(5, part1(testLines))
    verify(12, part2(testLines))

    // ---- RUN

    val lines = File("src/day05/input.txt").read()

    verify(6113, part1(lines))
    verify(20373, part2(lines))
}