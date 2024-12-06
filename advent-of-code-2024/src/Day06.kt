enum class Direction(val x: Int, val y: Int) {
    UP(0, -1),
    RIGHT(1, 0),
    DOWN(0, 1),
    LEFT(-1, 0);

    fun rotate() = entries[(ordinal + 1) % entries.size]
}

sealed class Field {
    data object Guard : Field()
    data object Wall : Field()
    data object NotVisited : Field()
    data class Visited(val directions: Set<Direction>) : Field()
    data object NotExists : Field()
}

data class Array2d(val xSize: Int, val ySize: Int) {

    private val array = Array(ySize) { Array<Field>(xSize) { Field.NotVisited } }

    operator fun get(x: Int, y: Int) = if (isInBounds(x, y)) array[y][x] else Field.NotExists
    operator fun set(x: Int, y: Int, field: Field) {
        array[y][x] = field
    }

    fun isInBounds(x: Int, y: Int) = x in 0 until xSize && y in 0 until ySize

    fun countVisited() = array.sumOf { it.count { it is Field.Visited || it is Field.Guard } }

    fun printArray() {
        for (x in array.indices) {
            for (y in array[x].indices) {
                print(
                    when (array[x][y]) {
                        Field.Guard -> '^'
                        Field.Wall -> '#'
                        Field.NotVisited -> '.'
                        is Field.Visited -> 'X'
                        else -> ' '
                    }
                )
            }
            println("")
        }
        println("")
    }
}

data class Point(val x: Int, val y: Int)

fun main() {

    fun part1(input: List<String>): Int {

        val array = Array2d(input[0].length, input.size)
        var guardPos = Point(0, 0)
        var direction = Direction.UP

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
                            Field.Guard
                        }

                        else -> error("Unknown char: $char")
                    }
                )
            }
        }

        do {
            // array.printArray()
            while (array[guardPos.x + direction.x, guardPos.y + direction.y] == Field.Wall) {
                direction = direction.rotate()
            }
            array[guardPos.x, guardPos.y] = Field.Visited(emptySet())
            guardPos = Point(guardPos.x + direction.x, guardPos.y + direction.y)


        } while (array[guardPos.x, guardPos.y] != Field.NotExists)

        return array.countVisited()
    }

    fun part2(input: List<String>): Int {

        val array = Array2d(input[0].length, input.size)
        var guardPos = Point(0, 0)
        var direction = Direction.UP
        var count = 0

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
                            Field.Guard
                        }

                        else -> error("Unknown char: $char")
                    }
                )
            }
        }

        do {
            // array.printArray()

            val directions = (array[guardPos.x, guardPos.y] as? Field.Visited)?.directions ?: emptySet()
            array[guardPos.x, guardPos.y] = Field.Visited(directions + direction)

            while (array[guardPos.x + direction.x, guardPos.y + direction.y] == Field.Wall) {
                direction = direction.rotate()
            }
            guardPos = Point(guardPos.x + direction.x, guardPos.y + direction.y)

            val tempDirection = direction.rotate()
            var tempGuardPos = guardPos
            var tempRoute: Field
            do {
                tempRoute = array[tempGuardPos.x, tempGuardPos.y]
                if (tempRoute is Field.Visited && tempRoute.directions.contains(tempDirection)) {
                    count++
                    break
                }
                tempGuardPos = Point(tempGuardPos.x + tempDirection.x, tempGuardPos.y + tempDirection.y)
            } while (tempRoute != Field.Wall && tempRoute != Field.NotExists)


        } while (array[guardPos.x, guardPos.y] != Field.NotExists)

        return count
    }

    val testInput = readInput("Day06_test")
    val input = readInput("Day06")

    test(41) { part1(testInput) }
    exec { part1(input) }
    test(6) { part2(testInput) }
    exec { part2(input) }
}
