import util.Array2d
import util.Point
import util.Vector

fun main() {

    fun part1(input: List<String>): Int {

        val array = Array2d.readChar(input)
        val mask: MutableSet<Point> = mutableSetOf()
        var count = 0

        array.forEach { x, y, _ ->
            val flow = Vector.DIRECTIONS_8.map { vector ->
                val point = Point(x, y)
                listOf(point, point + vector, point + vector * 2, point + vector * 3)
            }

            for (points in flow) {
                if (points.mapNotNull { array.getSafe(it) }.joinToString("") == "XMAS") {
                    mask.addAll(points)
                    count++
                }
            }
        }

        return count
    }

    fun part2(input: List<String>): Int {

        val array = Array2d.readChar(input)

        var count = 0

        array.forEach { x, y, _ ->
            when {
                array.getSafe(Point(x, y)) != 'A' -> Unit
                array.getSafe(Point(x+1, y+1)) == 'M' &&  array.getSafe(Point(x-1, y-1)) == 'S' && array.getSafe(Point(x+1, y-1)) == 'M' &&  array.getSafe(Point(x-1, y+1)) == 'S' -> count++
                array.getSafe(Point(x+1, y+1)) == 'M' &&  array.getSafe(Point(x-1, y-1)) == 'S' && array.getSafe(Point(x+1, y-1)) == 'S' &&  array.getSafe(Point(x-1, y+1)) == 'M' -> count++
                array.getSafe(Point(x+1, y+1)) == 'S' &&  array.getSafe(Point(x-1, y-1)) == 'M' && array.getSafe(Point(x+1, y-1)) == 'M' &&  array.getSafe(Point(x-1, y+1)) == 'S' -> count++
                array.getSafe(Point(x+1, y+1)) == 'S' &&  array.getSafe(Point(x-1, y-1)) == 'M' && array.getSafe(Point(x+1, y-1)) == 'S' &&  array.getSafe(Point(x-1, y+1)) == 'M' -> count++
                else -> Unit
            }
        }
        return count
    }

    val testInput = readInput("Day04_test")
    val input = readInput("Day04")

    test(18) { part1(testInput) }
    exec { part1(input) }
    test(9) { part2(testInput) }
    exec { part2(input) }
}
