fun main() {

    data class Point(val x: Int, val y: Int)

    fun List<List<Char>>.getCharSafe(point: Point): Char? {
        if (point.x < 0 || point.x >= size) return null
        if (point.y < 0 || point.y >= get(point.x).size) return null
        return get(point.x)[point.y]
    }

    fun part1(input: List<String>): Int {

        val array: List<List<Char>> = input.map { it.toCharArray().toList() }
        val mask: MutableSet<Point> = mutableSetOf()
        var count = 0

        for (x in array.indices) {
            for (y in array[x].indices) {
                val flow = listOf(
                    listOf(Point(x + 0, y + 0), Point(x + 0, y + 1), Point(x + 0, y + 2), Point(x + 0, y + 3)),
                    listOf(Point(x + 0, y + 0), Point(x + 0, y - 1), Point(x + 0, y - 2), Point(x + 0, y - 3)),
                    listOf(Point(x + 0, y + 0), Point(x + 1, y + 1), Point(x + 2, y + 2), Point(x + 3, y + 3)),
                    listOf(Point(x + 0, y + 0), Point(x + 1, y + 0), Point(x + 2, y + 0), Point(x + 3, y + 0)),
                    listOf(Point(x + 0, y + 0), Point(x + 1, y - 1), Point(x + 2, y - 2), Point(x + 3, y - 3)),
                    listOf(Point(x + 0, y + 0), Point(x - 1, y + 1), Point(x - 2, y + 2), Point(x - 3, y + 3)),
                    listOf(Point(x + 0, y + 0), Point(x - 1, y + 0), Point(x - 2, y + 0), Point(x - 3, y + 0)),
                    listOf(Point(x + 0, y + 0), Point(x - 1, y - 1), Point(x - 2, y - 2), Point(x - 3, y - 3)),
                )

                for (points in flow) {
                    if (points.mapNotNull { array.getCharSafe(it) }.joinToString("") == "XMAS") {
                        mask.addAll(points)
                        count++
                    }
                }
            }
        }
        return count
    }

    fun part2(input: List<String>): Int {
        val array: List<List<Char>> = input.map { it.toCharArray().toList() }
        var count = 0

        for (x in array.indices) {
            for (y in array[x].indices) {
                when {
                    array.getCharSafe(Point(x, y)) != 'A' -> continue
                    array.getCharSafe(Point(x+1, y+1)) == 'M' &&  array.getCharSafe(Point(x-1, y-1)) == 'S' && array.getCharSafe(Point(x+1, y-1)) == 'M' &&  array.getCharSafe(Point(x-1, y+1)) == 'S' -> count++
                    array.getCharSafe(Point(x+1, y+1)) == 'M' &&  array.getCharSafe(Point(x-1, y-1)) == 'S' && array.getCharSafe(Point(x+1, y-1)) == 'S' &&  array.getCharSafe(Point(x-1, y+1)) == 'M' -> count++
                    array.getCharSafe(Point(x+1, y+1)) == 'S' &&  array.getCharSafe(Point(x-1, y-1)) == 'M' && array.getCharSafe(Point(x+1, y-1)) == 'M' &&  array.getCharSafe(Point(x-1, y+1)) == 'S' -> count++
                    array.getCharSafe(Point(x+1, y+1)) == 'S' &&  array.getCharSafe(Point(x-1, y-1)) == 'M' && array.getCharSafe(Point(x+1, y-1)) == 'S' &&  array.getCharSafe(Point(x-1, y+1)) == 'M' -> count++
                    else -> continue
                }
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
