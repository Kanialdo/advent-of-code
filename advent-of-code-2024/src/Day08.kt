// https://adventofcode.com/2024/day/8

data class Point2(val x: Int, val y: Int)

data class Antinodes(val xSize: Int, val ySize: Int) {

    val array = Array(ySize) { Array(xSize) { false } }

    fun isInBounds(x: Int, y: Int) = x in 0 until xSize && y in 0 until ySize

    operator fun get(x: Int, y: Int) = if (isInBounds(x, y)) array[y][x] else null
    operator fun set(x: Int, y: Int, field: Boolean) {
        if (isInBounds(x, y)) {
            array[y][x] = field
        }
    }

    fun count() = array.sumOf { it.count { it } }

    fun printArray() {
        for (y in array.indices) {
            for (x in array[y].indices) {
                print(if (get(x, y)!!) "#" else ".")
            }
            println("")
        }
        println("")
    }
}

data class Antenas(val xSize: Int, val ySize: Int) {

    val array = Array(ySize) { Array<Char?>(xSize) { null } }

    operator fun get(x: Int, y: Int) = if (isInBounds(x, y)) array[y][x] else null
    operator fun set(x: Int, y: Int, field: Char) {
        array[y][x] = field
    }

    fun getPoints(): Map<Char, List<Point>> {
        val map: MutableMap<Char, MutableSet<Point>> = mutableMapOf()
        for (y in array.indices) {
            for (x in array[y].indices) {
                get(x, y)
                    ?.let { it.takeIf { it != '.' } }
                    ?.let { map[it] = map.getOrDefault(it, mutableSetOf()).apply { add(Point(x, y)) } }
            }
        }
        return map.mapValues { it.value.toList() }
    }

    fun isInBounds(x: Int, y: Int) = x in 0 until xSize && y in 0 until ySize

    fun printArray() {
        for (y in array.indices) {
            for (x in array[y].indices) {
                print(array[y][x])
            }
            println("")
        }
        println("")
    }
}

fun main() {

    fun part1(input: List<String>): Int {

        val antenas = Antenas(input[0].length, input.size)
        val antinodes = Antinodes(input[0].length, input.size)

        input.forEachIndexed { y, list ->
            list.forEachIndexed { x, char ->
                antenas[x, y] = char
            }
        }

        antenas.printArray()

        antenas.getPoints().forEach { (char, points) ->
            for (i in points.indices) {
                for (j in (i + 1) until points.size) {
                    val dx = points[i].x - points[j].x
                    val dy = points[i].y - points[j].y
                    listOf(
                        Point(points[i].x + dx, points[i].y + dy),
                        Point(points[j].x - dx, points[j].y - dy)
                    ).forEach {
                        antinodes[it.x, it.y] = true
                    }
                }
            }
        }

        antinodes.printArray()

        return antinodes.count()
    }

    fun part2(input: List<String>): Int {
        val antenas = Antenas(input[0].length, input.size)
        val antinodes = Antinodes(input[0].length, input.size)

        input.forEachIndexed { y, list ->
            list.forEachIndexed { x, char ->
                antenas[x, y] = char
            }
        }

        antenas.printArray()

        antenas.getPoints().forEach { (char, points) ->
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

        antinodes.printArray()

        return antinodes.count()
    }

    val testInput = readInput("Day08_test")
    val input = readInput("Day08")

    test(14) { part1(testInput) }
    exec { part1(input) }
    test(34) { part2(testInput) }
    exec { part2(input) }
}
