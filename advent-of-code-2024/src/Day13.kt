import util.Point
import util.PointOfLong
import util.Vector
import util.VectorOfLong
import kotlin.math.max
import kotlin.math.min

// https://adventofcode.com/2024/day/13

fun main() {

    data class Button(val cost: Int, val movement: VectorOfLong)

    data class Machine(
        val buttonA: Button,
        val buttonB: Button,
        val prize: PointOfLong
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
                    Button(3, VectorOfLong(xA.toLong(), yA.toLong())),
                    Button(1, VectorOfLong(xB.toLong(), yB.toLong())),
                    PointOfLong(xP.toLong(), yP.toLong())
                )
            }

        return machines.sumOf { m ->
            val results = mutableListOf<Int>()
            for (i in 0..100) {
                for (j in 0..100) {
                    val result = PointOfLong(0, 0) + m.buttonA.movement * i.toLong() + m.buttonB.movement * j.toLong()
                    if (result == m.prize) {
                        results += m.buttonA.cost * i + m.buttonB.cost * j
                    }
                }
            }
            results.minOrNull() ?: 0
        }
    }

    fun part2(input: List<String>): Long {

        val extra = 10000000000000

        val machines = input.filter { it.isNotEmpty() }
            .chunked(3)
            .map {
                val (xA, yA) = regex.find(it[0])!!.destructured
                val (xB, yB) = regex.find(it[1])!!.destructured
                val (xP, yP) = regex2.find(it[2])!!.destructured
                Machine(
                    Button(3, VectorOfLong(xA.toLong(), yA.toLong())),
                    Button(1, VectorOfLong(xB.toLong(), yB.toLong())),
                    PointOfLong(xP.toLong() + extra, yP.toLong() + extra)
                )
            }

        return machines.sumOf { m ->
            val results = mutableListOf<Long>()

            val initX = m.prize.x / (m.buttonA.movement.x + m.buttonB.movement.x)
            val initY = m.prize.y / (m.buttonA.movement.y + m.buttonB.movement.y)

            val init = min(initX, initY)

            for (i in 0..100000) {
                for (j in 0..100000) {
                    val result =
                        PointOfLong(
                            0,
                            0
                        ) + m.buttonA.movement * (init + i.toLong()) + m.buttonB.movement * (init + j.toLong())
                    if (i==0 && j==0){
                        println(result)
                    }
                    if (result == m.prize) {
                        results += m.buttonA.cost * (init + i.toLong()) + m.buttonB.cost * (init + j.toLong())
                    }

                }
            }
            results.minOrNull() ?: 0L
        }
    }

    val testInput = readInput("Day13_test")
    val input = readInput("Day13")

    test(480) { part1(testInput) }
    exec { part1(input) }
    execLong { part2(input) }
}
