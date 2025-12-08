fun main() {

    fun part1(input: List<String>): Long {
        val numbers = input.dropLast(1).map { it.split("\\s+".toRegex()).mapNotNull { it.toLongOrNull() } }
        val actions = input.last().split("\\s+".toRegex())

        return actions.mapIndexed { index, action ->
            when (action) {
                "*" -> numbers.map { it[index] }.reduce { acc, value -> acc * value }
                "+" -> numbers.map { it[index] }.reduce { acc, value -> acc + value }
                else -> error("Unsupported operation")
            }
        }.sum()
    }

    fun part2(input: List<String>): Long {
        TODO()
    }

    val testInput = readInput("Day06_test")
    val input = readInput("Day06")

    testLong(4277556) { part1(testInput) }
    execLong { part1(input) }

    testLong(TODO()) { part2(testInput) }
    execLong { part2(input) }
}
