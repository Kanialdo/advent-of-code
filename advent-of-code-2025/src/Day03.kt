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

    fun part2(input: List<String>): Int {
        TODO()
    }

    val testInput = readInput("Day03_test")
    val input = readInput("Day03")

    test(357) { part1(testInput) }
    exec { part1(input) }

    test(TODO()) { part2(testInput) }
    exec { part2(input) }
}
