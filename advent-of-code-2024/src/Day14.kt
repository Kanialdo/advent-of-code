import util.Point
import util.Vector
import kotlin.math.abs

// https://adventofcode.com/2024/day/14

fun main() {

    data class Robot(
        val position: Point,
        val vector: Vector
    )

    val regex = "p=(.*),(.*) v=(.*),(.*)".toRegex()

    fun List<String>.robots() = this.map {
        val (px, py, vx, vy) = regex.find(it)!!.destructured
        Robot(
            position = Point(
                x = px.toInt(),
                y = py.toInt(),
            ),
            vector = Vector(
                x = vx.toInt(),
                y = vy.toInt(),
            ),
        )
    }

    fun part1(input: List<String>, width: Int, height: Int): Int {

        val maxX = width
        val maxY = height

        var q1 = 0
        var q2 = 0
        var q3 = 0
        var q4 = 0

        input.robots()
            .forEach { robot: Robot ->
                var pos = robot.position
                println(pos)
                repeat(100) {
                    pos += robot.vector
                    pos = Point((pos.x + maxX) % maxX, (pos.y + maxY) % maxY)
                    println(pos)
                }
                when {
                    pos.x < (maxX / 2) && pos.y < (maxY / 2) -> q1++
                    pos.x > (maxX / 2) && pos.y < (maxY / 2) -> q2++
                    pos.x < (maxX / 2) && pos.y > (maxY / 2) -> q3++
                    pos.x > (maxX / 2) && pos.y > (maxY / 2) -> q4++
                }
            }

        println("$q1 $q2 $q3 $q4")
        return q1 * q2 * q3 * q4
    }

    fun part2(input: List<String>): Int {
        return TODO()
    }

    val testInput = readInput("Day14_test")
    val input = readInput("Day14")

    test(12) { part1(testInput, 11, 7) }
    exec { part1(input, 101, 103) }
    test(TODO()) { part2(testInput) }
    exec { part2(input) }
}
