import kotlin.math.abs
import kotlin.math.sign

fun main() {

    fun List<Int>.getInvalidIndex(): Int? {
        val sign = (get(0) - get(1)).sign

        for (i in 0..size - 2) {
            val different = get(i) != get(i + 1)
            val sameSign = (get(i) - get(i + 1)).sign == sign
            val diffOk = abs(get(i) - get(i + 1)) <= 3
            if (!(different && sameSign && diffOk)) {
                return i
            }
        }
        return null
    }

    fun part1(input: List<String>): Int {
        return input.count {
            val items = it.split(" ").map { it.toInt() }
            items.getInvalidIndex() == null
        }
    }

    fun part2(input: List<String>): Int {
        return input.count {
            val items = it.split(" ").map { it.toInt() }
            val invalidIndex = items.getInvalidIndex()
            when {
                invalidIndex == null -> true
                items.subList(1, items.size).getInvalidIndex() == null -> true
                items.subList(0, items.size - 1).getInvalidIndex() == null -> true
                items.toMutableList().apply { removeAt(invalidIndex) }.getInvalidIndex() == null -> true
                items.toMutableList().apply { removeAt(invalidIndex + 1) }.getInvalidIndex() == null -> true
                else -> false
            }
        }
    }


    val testInput = readInput("Day02_test")
    val input = readInput("Day02")

    test(2) { part1(testInput) }
    exec { part1(input) }
    test(4) { part2(testInput) }
    exec { part2(input) }
}
