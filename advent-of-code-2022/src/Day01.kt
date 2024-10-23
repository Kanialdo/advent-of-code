fun main() {

    fun part1(input: String): Int {
        val elves = input.parse()
        return elves.max()
    }

    fun part2(input: String): Int {
        val elves = input.parse()
        return elves.findTopNElves(3).sum()
    }

    val testInput = readInputAsText("Day01_test")
    check(part1(testInput), 24000)
    check(part2(testInput), 45000)

    val input = readInputAsText("Day01")
    println(part1(input))
    println(part2(input))
}

private fun String.parse(): List<Int> = split("\n\n").map { calories ->
    calories.lines().sumOf { it.toInt() }
}

private fun List<Int>.findTopNElves(n: Int): List<Int> {
    check(n < this.size)
    return this.sortedDescending()
        .take(n)
}