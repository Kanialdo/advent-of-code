import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {

        val movements = input.map {
            val number = it.drop(1).toInt()
            when (it.first()) {
                'L' -> -number
                'R' -> number
                else -> error("Invalid direction")
            }
        }

        var position = 50
        var zeros = 0

        movements.forEach { movement ->
            position = (position + movement + 100) % 100
            if (position == 0) zeros++
        }

        return zeros
    }

    fun part2(input: List<String>): Int {

        val movements = input.map {
            val number = it.drop(1).toInt()
            when (it.first()) {
                'L' -> -number
                'R' -> number
                else -> error("Invalid direction")
            }
        }

        var position = 50
        var zeros = 0

        movements.forEach { movement ->
            zeros += abs(movement / 100)
            val shortMovement = movement % 100
            when {
                position != 0 && position + shortMovement < 0 -> zeros++
                position != 0 && position + shortMovement > 100 -> zeros++
                (position + shortMovement) % 100 == 0 -> zeros++
            }
            position = (position + shortMovement + 100) % 100
        }

        return zeros
    }

    val testInput = readInput("Day01_test")
    val input = readInput("Day01")

    test(3) { part1(testInput) }
    exec { part1(input) }

    test(6) { part2(testInput) }
    exec { part2(input) }
}
