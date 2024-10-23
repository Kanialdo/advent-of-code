fun main() {

    data class Pos(val x: Int, val y: Int)

    fun part1(input: List<String>): Int {

        var h = Pos(0, 0)
        var t = Pos(0, 0)
        val tail = mutableSetOf(t)

        input.forEach {
            val direction = it.split(" ").first()
            val moves = it.split(" ").last().toInt()

            repeat(moves) {
                h = when (direction) {
                    "R" -> Pos(h.x + 1, h.y)
                    "L" -> Pos(h.x - 1, h.y)
                    "U" -> Pos(h.x, h.y + 1)
                    "D" -> Pos(h.x, h.y - 1)
                    else -> throw IllegalStateException("Unknown direction $direction")
                }

                t = when {
                    h.x == t.x + 2 -> Pos(t.x + 1, h.y)
                    h.x == t.x - 2 -> Pos(t.x - 1, h.y)
                    h.y == t.y + 2 -> Pos(h.x, t.y + 1)
                    h.y == t.y - 2 -> Pos(h.x, t.y - 1)
                    else -> t
                }

                tail.add(t)
            }
        }

        return tail.size
    }


    fun part2(input: List<String>): Int {
        return TODO()
    }

    val testInput = readInput("Day09_test")
    check(part1(testInput), 13)
    // check(part2(testInput), TODO)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}