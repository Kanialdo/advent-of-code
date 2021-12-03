package day02

import utils.verify
import java.io.File

fun main() {

    data class Move(val position: Int, val depth: Int)

    fun parseMove(value: String) = value.split(" ").let {
        when (it[0]) {
            "forward" -> Move(it[1].toInt(), 0)
            "up" -> Move(0, -it[1].toInt())
            "down" -> Move(0, it[1].toInt())
            else -> throw IllegalStateException()
        }
    }

    fun part1(moves: List<Move>): Int {
        var position = 0
        var depth = 0
        moves.forEach {
            position += it.position
            depth += it.depth
        }
        return position * depth
    }

    fun part2(moves: List<Move>): Int {
        var position = 0
        var depth = 0
        var aim = 0
        moves.forEach {
            position += it.position
            aim += it.depth
            depth += aim * it.position
        }
        return position * depth
    }

    // ----- TEST

    val testMoves = File("src/day02/input_test.txt").readLines().map(::parseMove)

    verify(150, part1(testMoves))
    verify(900, part2(testMoves))

    // ---- RUN

    val moves = File("src/day02/input.txt").readLines().map(::parseMove)

    verify(1924923, part1(moves))
    verify(1982495697, part2(moves))
}