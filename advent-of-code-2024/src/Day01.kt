import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {

        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()

        input.forEach {
            it.split("   ")
                .map { it.toInt() }
                .let {
                    left.add(it[0])
                    right.add(it[1])
                }
        }

        left.sort()
        right.sort()

        return left.mapIndexed { index, value -> abs(value - right[index]) }.sum()
    }

    fun part2(input: List<String>): Int {

        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()

        input.forEach {
            it.split("   ")
                .map { it.toInt() }
                .let {
                    left.add(it[0])
                    right.add(it[1])
                }
        }

        val right2 = right.groupBy { it }

        return left.sumOf { leftNumber -> leftNumber * (right2[leftNumber]?.size ?: 0) }
    }

    val testInput = readInput("Day01_test")
    val input = readInput("Day01")

    test(11) { part1(testInput) }
    exec { part1(input) }

    test(31) { part2(testInput) }
    exec { part2(input) }
}
