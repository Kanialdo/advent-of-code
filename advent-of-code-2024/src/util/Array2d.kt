package util

class Array2d<Type>(
    val width: Int,
    val height: Int,
    val defaultValue: Type
) {

    private val array: MutableList<MutableList<Type>> = MutableList(height) { MutableList(width) { defaultValue } }

    operator fun get(point: Point) = get(point.x, point.y)
    operator fun get(x: Int, y: Int): Type = array[y][x]

    fun getSafe(point: Point) = getSafe(point.x, point.y)
    fun getSafe(x: Int, y: Int) = if (isInBounds(x, y)) array[y][x] else null

    operator fun set(point: Point, value: Type) = set(point.x, point.y, value)
    operator fun set(x: Int, y: Int, value: Type) {
        array[y][x] = value
    }

    fun setSafe(point: Point, value: Type) = setSafe(point.x, point.y, value)
    fun setSafe(x: Int, y: Int, value: Type) {
        if (isInBounds(x, y)) array[y][x] = value
    }

    fun isInBounds(point: Point) = isInBounds(point.x, point.y)
    fun isInBounds(x: Int, y: Int) = x in 0 until width && y in 0 until height

    fun findAny(filter: (Type) -> Boolean): Point? {
        for (y in array.indices) {
            for (x in array[y].indices) {
                if (filter(array[y][x])) return Point(x, y)
            }
        }
        return null
    }

    fun findAll(filter: (Type) -> Boolean): List<Point> {
        val findings = mutableListOf<Point>()
        for (y in array.indices) {
            for (x in array[y].indices) {
                if (filter(array[y][x])) findings.add(Point(x, y))
            }
        }
        return findings
    }

    fun forEach(action: (x: Int, y: Int, value: Type) -> Unit) {
        for (y in array.indices) {
            for (x in array[y].indices) {
                action(x, y, array[y][x])
            }
        }
    }

    fun count(filter: (Type) -> Boolean): Int = array.sumOf { it.count { filter(it) } }

    fun createMask(): Array2d<Boolean> = Array2d(width, height, false)

    fun print(mapper: (Type) -> Char) {
        for (y in array.indices) {
            for (x in array[y].indices) {
                print(mapper(array[y][x]))
            }
            println()
        }
        println()
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

fun Array2d<Char>.printChars() = print { it }

fun Array2d<Boolean>.printMask() = print { if (it) '#' else '.' }
