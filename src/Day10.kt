fun main() {

    fun part1(input: List<String>): Int {

        var cycle = 0
        var value = 1
        var output = 0

        val checkpoints = listOf(20, 60, 100, 140, 180, 220)

        fun checkpoint() {
            if (cycle in checkpoints) {
                output += cycle * value
            }
        }

        input.forEachIndexed { index, instruction ->
            if (instruction.startsWith("addx")) {
                cycle++
                checkpoint()
                cycle++
                checkpoint()
                value += instruction.split(" ").last().toInt()
            } else {
                cycle++
                checkpoint()
            }
        }
        return output
    }

    fun part2(input: List<String>): Int {
        return TODO()
    }

    val testInput = readInput("Day10_test")
    check(part1(testInput), 13140)
    // check(part2(testInput), TODO)

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}