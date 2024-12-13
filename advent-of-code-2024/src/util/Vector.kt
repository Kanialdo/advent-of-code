package util

data class Vector(val x: Int, val y: Int) {

    operator fun times(scalar: Int) = Vector(x * scalar, y * scalar)

    fun rotate90() = Vector(-y, x)

    override fun toString(): String {
        return "[$x, $y]"
    }

    companion object {
        val UP = Vector(0, -1)
        val UP_RIGHT = Vector(1, -1)
        val RIGHT = Vector(1, 0)
        val DOWN_RIGHT = Vector(1, 1)
        val DOWN = Vector(0, 1)
        val DOWN_LEFT = Vector(-1, 1)
        val LEFT = Vector(-1, 0)
        val UP_LEFT = Vector(-1, -1)

        val DIRECTIONS_4 = listOf(UP, RIGHT, DOWN, LEFT)
        val DIRECTIONS_8 = listOf(UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT)
    }
}

data class VectorOfLong(val x: Long, val y: Long) {

    operator fun times(scalar: Long) = VectorOfLong(x * scalar, y * scalar)

    fun rotate90() = VectorOfLong(-y, x)

    override fun toString(): String {
        return "[$x, $y]"
    }

    companion object {
        val UP = Vector(0, -1)
        val UP_RIGHT = Vector(1, -1)
        val RIGHT = Vector(1, 0)
        val DOWN_RIGHT = Vector(1, 1)
        val DOWN = Vector(0, 1)
        val DOWN_LEFT = Vector(-1, 1)
        val LEFT = Vector(-1, 0)
        val UP_LEFT = Vector(-1, -1)

        val DIRECTIONS_4 = listOf(UP, RIGHT, DOWN, LEFT)
        val DIRECTIONS_8 = listOf(UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT)
    }
}
