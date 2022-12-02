fun main() {

    fun part1(input: String): Int {
        val rounds = input.lines()
        var score = 0
        rounds.forEach { round ->
            val left = round[0].code - 'A'.code
            val right = round[2].code - 'X'.code
            score += (right - left + 4) % 3 * 3 + right % 3 + 1
        }
        return score
    }

    fun part2(input: String): Int {
        val rounds = input.lines()
        var score = 0
        rounds.forEach { round ->
            val left = round[0].code - 'A'.code
            val right = round[2].code - 'X'.code
            score += (left + right + 2) % 3 + 1 + right * 3
        }
        return score
    }

    val testInput = readInputAsText("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInputAsText("Day02")
    println(part1(input))
    println(part2(input))
}