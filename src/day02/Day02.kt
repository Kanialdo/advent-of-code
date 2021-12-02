package day02

import java.io.File

object Day02 {

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

    fun parseMove(value: String) = value.split(" ").let {
        when (it[0]) {
            "forward" -> Move(it[1].toInt(), 0)
            "up" -> Move(0, -it[1].toInt())
            "down" -> Move(0, it[1].toInt())
            else -> throw IllegalStateException()
        }
    }

    data class Move(val position: Int, val depth: Int)
}

fun main() {

    val moves = File("src/day02/input.txt").readLines().map(Day02::parseMove)

    println("Result - part1: ${Day02.part1(moves)}")
    println("Result - part2: ${Day02.part2(moves)}")
}