fun main() {

    val pattern = "mul\\(\\d*,\\d*\\)".toRegex()

    fun String.runProgram() = pattern.findAll(this)
        .sumOf { finding ->
            finding.value
                .drop(4)
                .dropLast(1)
                .split(",")
                .map { it.toInt() }
                .let { it[0] * it[1] }
        }

    fun part1(input: List<String>): Int {

        val result = input.sumOf { line -> line.runProgram() }

        return result
    }

    fun part2(input: List<String>): Int {

        val result = run {
            val substrings = input.joinToString("").split("don't()")
            var sum = 0
            substrings.forEachIndexed { index, string ->
                sum += when {
                    index == 0 -> string.runProgram()
                    else -> string.split("do()").drop(1).sumOf { it.runProgram() }
                }
            }
            sum
        }

        return result
    }

    val testInput = readInput("Day03_test")
    val test2Input = readInput("Day03_test2")
    val input = readInput("Day03")

    test(161) { part1(testInput) }
    exec { part1(input) }
    test(48) { part2(test2Input) }
    exec { part2(input) }
}
