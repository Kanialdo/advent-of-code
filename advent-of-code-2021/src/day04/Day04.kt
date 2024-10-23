package day04

import utils.verify
import java.io.File

/** https://adventofcode.com/2021/day/4 */

fun main() {

    data class Board(private val values: Array<Array<Int?>>) {
        fun checkNumber(number: Int) {
            values.forEachIndexed { x, row ->
                row.forEachIndexed { y, value ->
                    if (value == number) {
                        values[x][y] = null
                    }
                }
            }
        }

        fun isCompleted(): Boolean {
            val isXCompleted = mutableListOf(true, true, true, true, true)
            val isYCompleted = mutableListOf(true, true, true, true, true)
            for (x in isXCompleted.indices) {
                for (y in isYCompleted.indices) {
                    isXCompleted[x] = isXCompleted[x] && values[x][y] == null
                    isYCompleted[y] = isYCompleted[y] && values[x][y] == null
                }
            }
            return (isXCompleted + isYCompleted).any { it }
        }

        fun sum() = values.sumOf { it.sumOf { it ?: 0 } }
    }

    data class Bingo(val numbers: List<Int>, val boards: List<Board>)

    fun File.readBingo() = readLines().let {
        Bingo(numbers = it.first().split(",").map(String::toInt),
            boards = it.subList(1, it.size).chunked(6).map { textBoard ->
                Board(
                    arrayOf(
                        textBoard[1].trim().replace("  ", " ").split(" ").map(String::toInt).toTypedArray(),
                        textBoard[2].trim().replace("  ", " ").split(" ").map(String::toInt).toTypedArray(),
                        textBoard[3].trim().replace("  ", " ").split(" ").map(String::toInt).toTypedArray(),
                        textBoard[4].trim().replace("  ", " ").split(" ").map(String::toInt).toTypedArray(),
                        textBoard[5].trim().replace("  ", " ").split(" ").map(String::toInt).toTypedArray(),
                    )
                )
            }
        )
    }

    fun part1(bingo: Bingo): Int {
        bingo.numbers.forEach { number ->
            bingo.boards.forEach { board ->
                board.checkNumber(number)
                if (board.isCompleted()) {
                    return board.sum() * number
                }
            }
        }
        throw Error()
    }

    fun part2(bingo: Bingo): Int {
        var lastScore: Int = -1
        bingo.numbers.forEach { number ->
            bingo.boards.forEach { board ->
                board.checkNumber(number)
                if (board.isCompleted()) {
                    lastScore = board.sum() * number
                }
                if (bingo.boards.firstOrNull { !it.isCompleted() } == null) {
                    return lastScore
                }
            }
        }
        return lastScore
    }

// ---- TEST

    val testBingo = File("src/day04/input_test.txt").readBingo()

    verify(4512, part1(testBingo))
    verify(1924, part2(testBingo))

// ---- RUN

    val bingo = File("src/day04/input.txt").readBingo()

    verify(27027, part1(bingo))
    verify(36975, part2(bingo))
}