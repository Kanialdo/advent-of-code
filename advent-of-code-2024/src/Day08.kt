// https://adventofcode.com/2024/day/8

data class Point2(val x: Int, val y: Int)

data class Antinodes(val xSize: Int, val ySize: Int) {

    val array = Array(ySize) { Array<Boolean>(xSize) { false } }

    fun isInBounds(x: Int, y: Int) = x in 0 until xSize && y in 0 until ySize

    operator fun get(x: Int, y: Int) = if (isInBounds(x, y)) array[y][x] else null
    operator fun set(x: Int, y: Int, field: Boolean) {
        array[y][x] = field
    }
}

data class Antenas(val xSize: Int, val ySize: Int) {

    val array = Array(ySize) { Array<Char?>(xSize) { null } }

    operator fun get(x: Int, y: Int) = if (isInBounds(x, y)) array[y][x] else null
    operator fun set(x: Int, y: Int, field: Char) {
        array[y][x] = field
    }

    fun getPoints():Map<Char, List<Point>> {
        val chars = mutableSetOf<Char>()

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

        return TODO()
    }

    fun part2(input: List<String>): Int {
        return TODO()
    }

    val testInput = readInput("Day08_test")
    val input = readInput("Day08")

    test(14) { part1(testInput) }
    exec { part1(input) }
    test(TODO()) { part2(testInput) }
    exec { part2(input) }
}
