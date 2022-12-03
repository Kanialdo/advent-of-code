fun main() {

    fun Char.priority() = if (this.isLowerCase()) {
        this.code - 'a'.code + 1
    } else {
        this.code - 'A'.code + 27
    }

    fun part1(input: String): Int {
        val rucksacks = input.lines()
        return rucksacks.sumOf { rucksack ->
            rucksack.toList().let {
                val left = it.subList(0, it.size / 2).sorted()
                val right = it.subList(it.size / 2, it.size).sorted()
                left.forEach { l ->
                    right.forEach { r ->
                        if (l == r) {
                            return@sumOf l.priority()
                        }
                    }
                }
            }
            0
        }
    }

    fun part2(input: String): Int {
        val rucksacks = input.lines()
        return rucksacks.chunked(3).sumOf {
            val sequence = it.map { it.toSet() }.flatten().sorted()
            sequence.windowed(3).forEach {
                if (it[0] == it[1] && it[1] == it[2]) {
                    return@sumOf it[0].priority()
                }
            }
            0
        }
    }

    val testInput = readInputAsText("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInputAsText("Day03")
    println(part1(input))
    println(part2(input))
}