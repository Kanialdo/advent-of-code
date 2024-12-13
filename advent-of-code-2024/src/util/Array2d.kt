package util

data class Vector(val x: Int, val y: Int) {

    operator fun times(scalar: Int) = Vector(x * scalar, y * scalar)

    override fun toString(): String {
        return "[$x, $y]"
    }

    companion object {
        private val UP = Vector(0, -1)
        private val DOWN = Vector(0, 1)
        private val LEFT = Vector(-1, 0)
        private val RIGHT = Vector(1, 0)

        val DIRECTIONS_4 = listOf(UP, RIGHT, DOWN, LEFT)
    }
}

data class Point(val x: Int, val y: Int) {

    operator fun plus(vector: Vector) = Point(x + vector.x, y + vector.y)

    override fun toString(): String {
        return "[$x, $y]"
    }
}


class Array2d<Type>(
    val width: Int,
    val height: Int,
    val defaultValue: Type
) {

    private val array: MutableList<MutableList<Type>> = MutableList(height) { MutableList(width) { defaultValue } }

    operator fun get(x: Int, y: Int): Type = array[y][x]
    operator fun set(x: Int, y: Int, value: Type) {
        array[y][x] = value
    }

    operator fun set(point: Point, value: Type) = set(point.x, point.y, value)

    operator fun get(point: Point) = get(point.x, point.y)

    fun getSafe(point: Point) = getSafe(point.x, point.y)
    fun getSafe(x: Int, y: Int) = if (isInBounds(x, y)) array[y][x] else null

    fun forEach(action: (x: Int, y: Int, value: Type) -> Unit) {
        for (y in array.indices) {
            for (x in array[y].indices) {
                action(x, y, array[y][x])
            }
        }
    }

    fun getPositions(value: Type): List<Point> {
        return array.mapIndexed { y, list -> list.mapIndexed { x, v -> if (v == value) Point(x, y) else null } }
            .flatten()
            .filterNotNull()
    }

    fun isInBounds(point: Point) = isInBounds(point.x, point.y)

    fun isInBounds(x: Int, y: Int) = x in 0 until width && y in 0 until height

    fun createMask(): Array2d<Boolean> = Array2d(width, height, false)

    fun print(mapper: (Type) -> Char) {
        for (y in array.indices) {
            for (x in array[y].indices) {
                print(mapper(array[x][y]))
            }
            println("")
        }
        println("")
    }

    companion object {

        fun <Type> read(input: List<String>, initValue: Type, mapper: (Char) -> Type): Array2d<Type> {
            val array = Array2d(input[0].length, input.size, initValue)
            input.forEachIndexed { y, list ->
                list.forEachIndexed { x, char ->
                    array[x, y] = mapper(char)
                }
            }
            return array
        }

        fun readChar(input: List<String>): Array2d<Char> = read(input = input, initValue = ' ', mapper = { it })
        fun readNumbers(input: List<String>): Array2d<Int> =
            read(input = input, initValue = -1, mapper = { it.digitToInt() })
    }
}
