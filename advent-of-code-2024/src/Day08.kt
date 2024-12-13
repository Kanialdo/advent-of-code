import util.*
import util.Array2d

// https://adventofcode.com/2024/day/8

private fun Array2d<Char>.getPoints(): Map<Char, List<Point>> {
    val map: MutableMap<Char, MutableSet<Point>> = mutableMapOf()
    this.forEach { x, y, value ->
        value
            .let { it.takeIf { it != '.' } }
            ?.let { map[it] = map.getOrDefault(it, mutableSetOf()).apply { add(Point(x, y)) } }
    }
    return map.mapValues { it.value.toList() }
}

fun main() {

    fun part1(input: List<String>): Int {

        val antennas = Array2d.readChar(input)
        val antinodes = antennas.createMask()

        antennas.printChars()

        antennas.getPoints().forEach { (_, points) ->
            for (i in points.indices) {
                for (j in (i + 1) until points.size) {
                    val dx = points[i].x - points[j].x
                    val dy = points[i].y - points[j].y
                    listOf(
                        Point(points[i].x + dx, points[i].y + dy),
                        Point(points[j].x - dx, points[j].y - dy)
                    ).forEach {
                        antinodes.setSafe(it.x, it.y, true)
                    }
                }
            }
        }

        antinodes.printMask()

        return antinodes.count { it }
    }

    fun part2(input: List<String>): Int {

        val antennas = Array2d.readChar(input)
        val antinodes = antennas.createMask()

        input.forEachIndexed { y, list ->
            list.forEachIndexed { x, char ->
                antennas[x, y] = char
            }
        }

        antennas.printChars()

        antennas.getPoints().forEach { (_, points) ->
            for (i in points.indices) {
                for (j in (i + 1) until points.size) {
                    val dx = points[i].x - points[j].x
                    val dy = points[i].y - points[j].y

                    var newPoint = Point(points[i].x, points[i].y)
                    while (antinodes.isInBounds(newPoint.x, newPoint.y)) {
                        antinodes[newPoint.x, newPoint.y] = true
                        newPoint = Point(newPoint.x + dx, newPoint.y + dy)
                    }

                    newPoint = Point(newPoint.x - dx, newPoint.y - dy)
                    while (antinodes.isInBounds(newPoint.x, newPoint.y)) {
                        antinodes[newPoint.x, newPoint.y] = true
                        newPoint = Point(newPoint.x - dx, newPoint.y - dy)
                    }
                }
            }
        }

        antinodes.printMask()

        return antinodes.count { it }
    }

    val testInput = readInput("Day08_test")
    val input = readInput("Day08")

    test(14) { part1(testInput) }
    exec { part1(input) }
    test(34) { part2(testInput) }
    exec { part2(input) }
}
