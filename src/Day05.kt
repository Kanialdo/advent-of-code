fun main() {

    val moveRegex = "move (\\d+) from (\\d+) to (\\d+)".toRegex()

    fun part1(input: String): String {
        val (stack, moves) = input.split("\n\n").map { it.lines() }
        val size = stack.last().trim().last().digitToInt()
        val data = List<ArrayDeque<Char>>(size) { ArrayDeque() }

        stack.dropLast(1).forEach { row ->
            row.chunked(4).forEachIndexed { index, value ->
                value[1].takeIf { it != ' ' }?.let { data[index].addFirst(it) }
            }
        }

        moves.forEach { move ->
            val (count, from, to) = moveRegex.find(move)?.groupValues?.drop(1)?.map { it.toInt() } ?: error("Not found")
            repeat(count) {
                data[to - 1].addLast(data[from - 1].removeLast())
            }
        }

        return data.map { it.last() }.joinToString(separator = "")
    }

    fun part2(input: String): String {
        val (stack, moves) = input.split("\n\n").map { it.lines() }
        val size = stack.last().trim().last().digitToInt()
        val data = List<ArrayDeque<Char>>(size) { ArrayDeque() }

        stack.dropLast(1).forEach { row ->
            row.chunked(4).forEachIndexed { index, value ->
                value[1].takeIf { it != ' ' }?.let { data[index].addFirst(it) }
            }
        }

        moves.forEach { move ->
            val (count, from, to) = moveRegex.find(move)?.groupValues?.drop(1)?.map { it.toInt() } ?: error("Not found")
            val buffer = ArrayDeque<Char>()
            repeat(count) {
                buffer.addFirst(data[from - 1].removeLast())
            }
            data[to - 1].addAll(buffer)
        }

        return data.map { it.last() }.joinToString(separator = "")
    }

    val testInput = readInputAsText("Day05_test")
    check(part1(testInput), "CMZ")
    check(part2(testInput), "MCD")

    val input = readInputAsText("Day05")
    println(part1(input))
    println(part2(input))
}