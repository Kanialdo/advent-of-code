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

    fun part2(input: List<String>): Int {
        TODO()
    }

    val testInput = readInput("Day05_test")
    val input = readInput("Day05")

    test(3) { part1(testInput) }
    exec { part1(input) }

    test(TODO()) { part2(testInput) }
    exec { part2(input) }
}
