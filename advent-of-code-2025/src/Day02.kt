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
        TODO()
    }

    val testInput = readInput("Day02_test").first().split(",")
    val input = readInput("Day02").first().split(",")

    testLong(1227775554) { part1(testInput) }
    execLong { part1(input) }

    testLong(TODO()) { part2(testInput) }
    execLong { part2(input) }
}
