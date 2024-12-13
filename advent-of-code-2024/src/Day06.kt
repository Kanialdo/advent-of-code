import util.Point
import util.Vector

sealed class Field {
    data object Guard : Field()
    data object Wall : Field()
    data object NotVisited : Field()
    data object Visited : Field()
    data object NotExists : Field()
}

data class Array2d(val xSize: Int, val ySize: Int) {

    val array = Array(ySize) { Array<Field>(xSize) { Field.NotVisited } }

    operator fun get(x: Int, y: Int) = if (isInBounds(x, y)) array[y][x] else Field.NotExists
    operator fun set(x: Int, y: Int, field: Field) {
        array[y][x] = field
    }

    fun isInBounds(x: Int, y: Int) = x in 0 until xSize && y in 0 until ySize

    fun count(field: Field) = array.sumOf { it.count { it == field } }

    fun printArray() {
        for (x in array.indices) {
            for (y in array[x].indices) {
                print(
                    when (array[x][y]) {
                        Field.Guard -> '^'
                        Field.Wall -> '#'
                        Field.NotVisited -> '.'
                        Field.Visited -> 'X'
                        else -> ' '
                    }
                )
            }
            println("")
        }
        println("")
    }
}

fun main() {

    fun part1(input: List<String>): Int {

        val array = Array2d(input[0].length, input.size)
        var guardPos = Point(0, 0)
        var direction = Vector.UP

        input.forEachIndexed { y, list ->
            list.forEachIndexed { x, char ->
                array.set(
                    x = x,
                    y = y,
                    field = when (char) {
                        '#' -> Field.Wall
                        '.' -> Field.NotVisited
                        '^' -> {
                            guardPos = Point(x, y)
                            Field.Visited
                        }

                        else -> error("Unknown char: $char")
                    }
                )
            }
        }

        do {
            // array.printArray()
            while (array[guardPos.x + direction.x, guardPos.y + direction.y] == Field.Wall) {
                direction = direction.rotate90()
            }
            array[guardPos.x, guardPos.y] = Field.Visited
            guardPos = Point(guardPos.x + direction.x, guardPos.y + direction.y)


        } while (array[guardPos.x, guardPos.y] != Field.NotExists)

        return array.count(Field.Visited)
    }

    fun part2(input: List<String>): Int {
        return TODO()
    }

    val testInput = readInput("Day06_test")
    val input = readInput("Day06")

    test(41) { part1(testInput) }
    exec { part1(input) }
    test(TODO()) { part2(testInput) }
    exec { part2(input) }
}
