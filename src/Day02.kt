fun main() {

    fun part1(input: String): Int {
        val rounds = input.lines()
        var score = 0
        rounds.forEach { round ->
            score += when (round) {
                "A X" -> 1 + 3
                "A Y" -> 2 + 6
                "A Z" -> 3 + 0
                "B X" -> 1 + 0
                "B Y" -> 2 + 3
                "B Z" -> 3 + 6
                "C X" -> 1 + 6
                "C Y" -> 2 + 0
                "C Z" -> 3 + 3
                else -> 0
            }
        }
        return score
    }

    fun part2(input: String): Int {
        val rounds = input.lines()
        var score = 0
        rounds.forEach { round ->
            score += when (round) {
                "A X" -> 3 + 0
                "A Y" -> 1 + 3
                "A Z" -> 2 + 6
                "B X" -> 1 + 0
                "B Y" -> 2 + 3
                "B Z" -> 3 + 6
                "C X" -> 2 + 0
                "C Y" -> 3 + 3
                "C Z" -> 1 + 6
                else -> 0
            }
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