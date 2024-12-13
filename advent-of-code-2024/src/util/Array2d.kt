package util

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

    fun setSafe(point: Point, value: Type) = setSafe(point.x, point.y, value)
    fun setSafe(x: Int, y: Int, value: Type) {
        if (isInBounds(x, y)) array[y][x] = value
    }

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

    fun count(filter: (Type) -> Boolean): Int = array.sumOf { it.count { filter(it) } }

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

fun Array2d<Char>.printChars() = print { it }

fun Array2d<Boolean>.printMask() = print { if (it) '#' else '.' }
