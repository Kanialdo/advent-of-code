fun main() {

    fun Char.priority() = if (isLowerCase()) {
        this.code - 'a'.code + 1
    } else {
        this.code - 'A'.code + 27
    }

    fun part1(input: String): Int {
        val rucksacks = input.lines()
        return rucksacks.sumOf { rucksack ->
            rucksack.chunked(size = rucksack.length / 2) // two parts of rucksack
                .map { it.toSet() } // unique values in each part
                .flatten().sorted() // sorted list with all unique chars from each part
                .reduce { acc, x -> if (acc == x) return@sumOf x.priority() else x } // value of first repeated char
            throw IllegalStateException()
        }
    }

    fun part2(input: String): Int {
        val rucksacks = input.lines()
        return rucksacks.chunked(3).sumOf { subRucksacks ->
            subRucksacks.map { it.toSet() } // every rucksack with unique values
                .flatten().sorted() // sorted list with all unique chars from each rucksack
                .windowed(3).first { it.toSet().size == 1 } // first sequence with same values
                .first().priority() // value of shared char
        }
    }

    val testInput = readInputAsText("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInputAsText("Day03")
    println(part1(input))
    println(part2(input))
}