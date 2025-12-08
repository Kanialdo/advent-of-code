import kotlin.math.max
import kotlin.math.min

fun main() {

    fun part1(input: List<String>): Int {

        val middle = input.indexOf("")
        val ranges = input.take(middle)
            .map { it.split("-").map { it.toLong() } }
            .map { it[0]..it[1] }
        val ingredients = input.drop(middle + 1).map { it.toLong() }

        return ingredients.count { ingredient ->
            ranges.any { range ->
                ingredient in range
            }
        }
    }

    fun part2(input: List<String>): Long {

        fun LongRange.isCrossing(other: LongRange): Boolean {
            return when {
                this.last >= other.first -> true
                else -> false
            }
        }

        fun LongRange.merge(other: LongRange): LongRange {
            return min(this.first, other.first)..max(this.last, other.last)
        }

        val middle = input.indexOf("")
        val ranges = input.take(middle)
            .map { it.split("-").map { it.toLong() } }
            .map { it[0]..it[1] }
            .sortedBy { it.first }

        val optimizedRanges = ranges.fold(mutableListOf<LongRange>()) { cache, current ->
            if (cache.lastOrNull()?.isCrossing(current) == true) {
                val last = cache.removeLast()
                cache.add(last.merge(current))
            } else {
                cache.add(current)
            }
            cache
        }

        return optimizedRanges.sumOf { it.last - it.first + 1 }
    }

    val testInput = readInput("Day05_test")
    val input = readInput("Day05")

    test(3) { part1(testInput) }
    exec { part1(input) }

    testLong(14) { part2(testInput) }
    execLong { part2(input) }
}
