import util.Point
import util.Vector

// https://adventofcode.com/2024/day/13

fun main() {

    data class Button(val cost: Int, val movement: Vector)

    data class Machine(
        val buttonA: Button,
        val buttonB: Button,
        val prize: Point
    )

    val regex = "Button .: X+(.*), Y+(.*)".toRegex()
    val regex2 = "Prize: X=(.*), Y=(.*)".toRegex()

    fun part1(input: List<String>): Int {

        val machines = input.filter { it.isNotEmpty() }
            .chunked(3)
            .map {
                val (xA, yA) = regex.find(it[0])!!.destructured
                val (xB, yB) = regex.find(it[1])!!.destructured
                val (xP, yP) = regex2.find(it[2])!!.destructured
                Machine(
                    Button(3, Vector(xA.toInt(), yA.toInt())),
                    Button(1, Vector(xB.toInt(), yB.toInt())),
                    Point(xP.toInt(), yP.toInt())
                )
            }

        return machines.sumOf { machine ->
            val results = mutableListOf<Int>()
            for (i in 0..100) {
                for (j in 0..100) {
                    val result = Point(0, 0) + machine.buttonA.movement * i + machine.buttonB.movement * j
                    if (result == machine.prize) {
                        results += machine.buttonA.cost * i + machine.buttonB.cost * j
                    }
                }
            }
            results.minOrNull() ?: 0
        }
    }

    fun part2(input: List<String>): Int {
        return TODO()
    }

    val testInput = readInput("Day13_test")
    val input = readInput("Day13")

    test(480) { part1(testInput) }
    exec { part1(input) }
    exec { part2(input) }
}
