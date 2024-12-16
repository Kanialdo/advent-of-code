import util.Array2d
import util.Vector

// https://adventofcode.com/2024/day/15
fun main() {

    val INSTRUTCION_MAPPING = mapOf(
        '>' to Vector.RIGHT,
        '<' to Vector.LEFT,
        '^' to Vector.UP,
        'v' to Vector.DOWN,
    )

    val WALL = '#'
    val EMPTY = '.'
    val MACHINE = '@'
    val BOX = 'O'
    val BOX_2_LEFT = '['
    val BOX_2_RIGHT = ']'

    fun Vector.isVertical() = this in listOf(Vector.UP, Vector.DOWN)

    fun part1(input: List<String>): Int {

        val map = Array2d.readChar(input.filter { it.contains(WALL) })
        val instructions = input.filter { it.any { it in INSTRUTCION_MAPPING.keys } }
            .flatMap { it.toList() }
            .mapNotNull { INSTRUTCION_MAPPING[it] }

        var position = map.findAny { it == MACHINE }!!

        instructions.forEach { vector ->
            var head = position
            val snake = mutableListOf(head)
            while (map[head + vector] == BOX) {
                head += vector
                snake.add(head)
            }
            if (map[head + vector] == EMPTY) {
                snake.reversed().forEachIndexed { index, point ->
                    map[point + vector] = map[point]
                    if (index == snake.size - 1) {
                        position = point + vector
                        map[point] = EMPTY
                    }
                }
            }
        }

        val boxes = map.findAll { it == BOX }

        return boxes.sumOf { 100 * it.y + it.x }
    }

    fun part2(input: List<String>): Int {
        return TODO()
    }

    val testInput = readInput("Day15_test")
    val input = readInput("Day15")

    test(10092) { part1(testInput) }
    exec { part1(input) }
    test(9021) { part2(testInput) }
    exec { part2(input) }
}
