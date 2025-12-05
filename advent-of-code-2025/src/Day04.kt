fun main() {

    fun part1(input: List<String>): Int {

        var count = 0
        val minSize = 0
        val maxSize = input.size - 1

        for (y in input.indices) {
            for (x in input.first().indices) {
                var rollsAround = 0
                if (input[y][x] == '@') {
                    if (y > minSize && x > minSize && input[y - 1][x - 1] == '@') rollsAround++
                    if (y > minSize && input[y - 1][x] == '@') rollsAround++
                    if (y > minSize && x < maxSize && input[y - 1][x + 1] == '@') rollsAround++
                    if (x > minSize && input[y][x - 1] == '@') rollsAround++
                    if (x < maxSize && input[y][x + 1] == '@') rollsAround++
                    if (y < maxSize && x > minSize && input[y + 1][x - 1] == '@') rollsAround++
                    if (y < maxSize && input[y + 1][x] == '@') rollsAround++
                    if (y < maxSize && x < maxSize && input[y + 1][x + 1] == '@') rollsAround++
                    if (rollsAround < 4) count++
                }
                if (rollsAround < 4) print('x') else print('.')
            }
            println()
        }

        return count
    }

    fun part2(input: List<String>): Int {
        TODO()
    }

    val testInput = readInput("Day04_test")
    val input = readInput("Day04")

    test(13) { part1(testInput) }
    exec { part1(input) }

    test(TODO()) { part2(testInput) }
    exec { part2(input) }
}
