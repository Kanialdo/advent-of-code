import kotlin.math.pow

fun main() {

    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val numbers = line.map { it.digitToInt() }
            val max = numbers.dropLast(1).max()
            val leftMaxIndex = numbers.indexOf(max)
            val sublist = numbers.drop(leftMaxIndex + 1)
            val secondMax = sublist.max()
            val result = max * 10 + secondMax
            println("$line -> $result")
            result
        }
    }

    fun part2(input: List<String>): Long {
        return input.sumOf { line ->
            var numbers = line.map { it.digitToInt() }
            var output = 0L
            for (i in 11.downTo(0)) {
                val max = numbers.dropLast(i).max()
                val maxIndex = numbers.indexOf(max)
                numbers = numbers.drop(maxIndex + 1)
                output += max * 10.0.pow(i).toLong()
            }
            println("$line -> $output")
            output
        }
    }

    val testInput = readInput("Day03_test")
    val input = readInput("Day03")

    test(357) { part1(testInput) }
    exec { part1(input) }

    testLong(3121910778619) { part2(testInput) }
    execLong { part2(input) }
}
