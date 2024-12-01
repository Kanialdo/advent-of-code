import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {

        val data = input.map { it.split("   ").map { it.toInt() } }

        val left = data.map { it[0] }.sorted()
        val right = data.map { it[1] }.sorted()

        return left.mapIndexed { index, value -> abs(value - right[index]) }.sum()
    }

    fun part2(input: List<String>): Int {

        val data = input.map { it.split("   ").map { it.toInt() } }

        val left = data.map { it[0] }.sorted()
        val right = data.map { it[1] }.sorted()

        return left.sumOf { leftNumber -> leftNumber * right.count { rightNumber -> leftNumber == rightNumber } }
    }

    val testInput = readInput("Day01_test")
    val input = readInput("Day01")

    check(part1(testInput) == 11)
    part1(input).println()

    check(part2(testInput) == 31)
    part2(input).println()
}
