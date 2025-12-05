fun main() {

    fun part1(input: List<String>): Long {
        return input.sumOf {
            val (from, to) = it.split("-").map { str -> str.toLong() }
            (from..to).sumOf { number ->
                val str = number.toString()
                when {
                    str.length % 2 != 0 -> 0L
                    str.take(str.length / 2) == str.takeLast(str.length / 2) -> number
                    else -> 0L
                }
            }
        }
    }

    fun part2(input: List<String>): Long {

        fun String.check(size: Int): Boolean {
            if (this.length < size * 2) return false
            val pattern = this.take(size)
            return this.chunked(size).all { it == pattern }
        }

        return input.sumOf {
            val (from, to) = it.split("-").map { str -> str.toLong() }
            (from..to).sumOf { number ->
                val str = number.toString()
                if (
                    str.check(1) ||
                    str.check(2) ||
                    str.check(3) ||
                    str.check(4) ||
                    str.check(5)
                ) number else 0L
            }
        }
    }

    val testInput = readInput("Day02_test").first().split(",")
    val input = readInput("Day02").first().split(",")

    testLong(1227775554) { part1(testInput) }
    execLong { part1(input) }

    testLong(4174379265) { part2(testInput) }
    execLong { part2(input) }
}
