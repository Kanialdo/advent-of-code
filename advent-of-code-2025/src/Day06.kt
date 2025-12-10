fun main() {

    fun part1(input: List<String>): Long {
        val numbers = input.dropLast(1).map { it.split("\\s+".toRegex()).mapNotNull { it.toLongOrNull() } }
        val actions = input.last().trim().split("\\s+".toRegex())

        return actions.mapIndexed { index, action ->
            when (action) {
                "*" -> numbers.map { it[index] }.reduce { acc, value -> acc * value }
                "+" -> numbers.map { it[index] }.reduce { acc, value -> acc + value }
                else -> error("Unsupported operation: [$action]")
            }
        }.sum()
    }

    fun part2(input: List<String>): Long {
        val numbers = input.dropLast(1)
        val actions = input.last().trim().split("\\s+".toRegex())

        var pointer = 0
        var tempValue = 0L
        var sum = 0L

        for (i in input.first().indices) {
            val value = numbers.map { it[i] }.joinToString("").trim()
            if (value.isNotEmpty()) {
                when (actions[pointer]) {
                    "*" -> tempValue = (if (tempValue == 0L) 1 else tempValue) * value.toLong()
                    "+" -> tempValue += value.toLong()
                }
            } else {
                sum += tempValue
                pointer++
                tempValue = 0
            }
        }

        sum += tempValue
        return sum
    }

    val testInput = readInput("Day06_test", trim = false)
    val input = readInput("Day06", trim = false)

    testLong(4277556) { part1(testInput) }
    execLong { part1(input) }

    testLong(3263827) { part2(testInput) }
    execLong { part2(input) }
}
