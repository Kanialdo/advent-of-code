fun main() {

    fun part1(input: List<String>): Long {

        return input.sumOf {
            var sum = 0L
            val (from, to) = it.split("-").map { it.toLong() }
            for (i in from..to) {
                val number = i.toString()
                if (number.length % 2 == 0) {
                    if (number.take(number.length / 2) == number.takeLast(number.length / 2)) {
                        sum += i
                    }
                }
            }
            sum
        }
    }

    fun part2(input: List<String>): Long {

        return input.sumOf {
            var sum = 0L
            val (from, to) = it.split("-").map { it.toLong() }
            for (i in from..to) {
                val number = i.toString()
                sum += when {
                    number.length >= 2 && number.chunked(1).all { it == number.take(1) } -> i
                    number.length >= 4 && number.chunked(2).all { it == number.take(2) } -> i
                    number.length >= 6 && number.chunked(3).all { it == number.take(3) } -> i
                    number.length >= 8 && number.chunked(4).all { it == number.take(4) } -> i
                    number.length >= 10 && number.chunked(5).all { it == number.take(5) } -> i
                    else -> 0
                }
            }
            sum
        }
    }

    val testInput = readInput("Day02_test").first().split(",")
    val input = readInput("Day02").first().split(",")

    testLong(1227775554) { part1(testInput) }
    execLong { part1(input) }

    testLong(4174379265) { part2(testInput) }
    execLong { part2(input) }
}
