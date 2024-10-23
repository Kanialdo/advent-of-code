package day10

import utils.verify
import java.io.File

/** https://adventofcode.com/2021/day/10 */

fun main() {

    val regex = "\\(\\)|\\[\\]|\\{\\}|\\<\\>".toRegex()

    fun String.reduce(): String {
        var temp = this
        while (regex.containsMatchIn(temp)) {
            temp = temp.replace("()", "")
                .replace("{}", "")
                .replace("<>", "")
                .replace("[]", "")
        }
        return temp
    }

    fun part1(lines: List<String>): Int {

        val score = mapOf(
            ')' to 3,
            ']' to 57,
            '}' to 1197,
            '>' to 25137,
        )

        return lines.sumOf { line ->
            line.reduce().let {
                val id = it.indexOfAny(score.keys.toCharArray())
                if (id != -1) score[it[id]]!! else 0
            }
        }
    }

    fun part2(lines: List<String>): Long {

        val score = mapOf(
            '(' to 1,
            '[' to 2,
            '{' to 3,
            '<' to 4,
        )

        return lines.map { line ->
            var result: Long = 0
            line.reduce().let { root ->
                if (root.all { it in score.keys }) {
                    root.reversed().forEach { result = 5 * result + score[it]!! }
                }
            }
            result
        }.filter { it != 0.toLong() }.sorted().let { it[it.size / 2] }
    }

    // ---- RUN

    val testLines = File("src/day10/input_test.txt").readLines()
    val lines = File("src/day10/input.txt").readLines()

    verify(26397, part1(testLines))
    verify(367059, part1(lines))

    verify(288957, part2(testLines))
    verify(1952146692, part2(lines))
}