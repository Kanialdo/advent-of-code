package util

data class Point(val x: Int, val y: Int) {

    operator fun plus(vector: Vector) = Point(x + vector.x, y + vector.y)
    operator fun minus(vector: Vector) = Point(x - vector.x, y - vector.y)

    override fun toString(): String {
        return "[$x, $y]"
    }
}

data class PointOfLong(val x: Long, val y: Long) {

    operator fun plus(vector: VectorOfLong) = PointOfLong(x + vector.x, y + vector.y)

    override fun toString(): String {
        return "[$x, $y]"
    }
}
