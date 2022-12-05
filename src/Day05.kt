fun main() {

    val moveRegex = "move (\\d+) from (\\d+) to (\\d+)".toRegex()

    fun part1(input: String): String {

        val (stack, moves) = input.split("\n\n")

        val size = stack.lines().last().split("   ").last().trim().toInt()
        val data = MutableList<ArrayDeque<Char>>(size) { ArrayDeque() }

        stack.lines().dropLast(1).forEach {
            it.chunked(4).forEachIndexed { index, value ->
                val char = value[1]
                if (char != ' ') {
                    data[index].addFirst(char)
                }
            }
        }

        moves.lines().forEach { move ->
            val (count, from, to) = moveRegex.find(move)?.groupValues?.drop(1)?.map { it.toInt() } ?: error("Not found")
            for (i in 0 until count) {
                data[to - 1].addLast(data[from - 1].removeLast())
            }
        }

        return data.map { it.last() }.joinToString(separator = "")
    }

    fun part2(input: String): String {

        val (stack, moves) = input.split("\n\n")

        val size = stack.lines().last().split("   ").last().trim().toInt()
        val data = MutableList<ArrayDeque<Char>>(size) { ArrayDeque() }

        stack.lines().dropLast(1).forEach {
            it.chunked(4).forEachIndexed { index, value ->
                val char = value[1]
                if (char != ' ') {
                    data[index].addFirst(char)
                }
            }
        }

        moves.lines().forEach { move ->
            val (count, from, to) = moveRegex.find(move)?.groupValues?.drop(1)?.map { it.toInt() } ?: error("Not found")
            var toBeMoved = mutableListOf<Char>()
            for (i in 0 until count) {
                toBeMoved.add(data[from - 1].removeLast())
            }
            data[to - 1].addAll(toBeMoved.reversed())
        }

        return data.map { it.last() }.joinToString(separator = "")
    }

    val testInput = readInputAsText("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInputAsText("Day05")
    println(part1(input))
    println(part2(input))
}