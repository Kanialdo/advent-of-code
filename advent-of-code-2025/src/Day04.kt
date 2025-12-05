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

        var count = 0
        val minSize = 0
        val maxSize = input.size - 1

        val currentInput = input.map { it.toMutableList() }.toMutableList()
        val toBeRemoved = mutableListOf<Pair<Int, Int>>()

        do {
            toBeRemoved.forEach {
                currentInput[it.first][it.second] = '.'
            }
            toBeRemoved.clear()
            for (y in currentInput.indices) {
                for (x in currentInput.first().indices) {
                    var rollsAround = 0
                    if (currentInput[y][x] == '@') {
                        if (y > minSize && x > minSize && currentInput[y - 1][x - 1] == '@') rollsAround++
                        if (y > minSize && currentInput[y - 1][x] == '@') rollsAround++
                        if (y > minSize && x < maxSize && currentInput[y - 1][x + 1] == '@') rollsAround++
                        if (x > minSize && currentInput[y][x - 1] == '@') rollsAround++
                        if (x < maxSize && currentInput[y][x + 1] == '@') rollsAround++
                        if (y < maxSize && x > minSize && currentInput[y + 1][x - 1] == '@') rollsAround++
                        if (y < maxSize && currentInput[y + 1][x] == '@') rollsAround++
                        if (y < maxSize && x < maxSize && currentInput[y + 1][x + 1] == '@') rollsAround++
                        if (rollsAround < 4) {
                            count++
                            toBeRemoved += y to x
                        }
                    }
                }
            }
        } while (toBeRemoved.isNotEmpty())
        return count
    }

    val testInput = readInput("Day04_test")
    val input = readInput("Day04")

    test(13) { part1(testInput) }
    exec { part1(input) }

    test(43) { part2(testInput) }
    exec { part2(input) }
}
