import kotlin.math.max
import kotlin.math.min

fun main() {

    fun part1(input: List<String>): Int {
        return input.count { line ->
            val (a1, a2, b1, b2) = line.split(",").map { it.split("-").map { it.toInt() } }.flatten()
            if (a2 - a1 >= b2 - b1) {
                a1 <= b1 && b2 <= a2
            } else {
                b1 <= a1 && a2 <= b2
            }
        }
    }

    fun part2(input: List<String>): Int {
        return input.count { line ->
            val (a1, a2, b1, b2) = line.split(",").map { it.split("-").map { it.toInt() } }.flatten()
            min(a2, b2) - max(a1, b1) >= 0
        }
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}