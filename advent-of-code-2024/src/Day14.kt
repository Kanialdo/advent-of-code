import util.Point
import util.Vector
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.imageio.ImageWriter
import javax.swing.text.Position
import kotlin.math.abs

// https://adventofcode.com/2024/day/14

fun main() {

    data class Robot(
        var position: Point,
        val vector: Vector
    )

    val regex = "p=(.*),(.*) v=(.*),(.*)".toRegex()

    fun List<String>.robots() = this.map {
        val (px, py, vx, vy) = regex.find(it)!!.destructured
        Robot(
            position = Point(
                x = px.toInt(),
                y = py.toInt(),
            ),
            vector = Vector(
                x = vx.toInt(),
                y = vy.toInt(),
            ),
        )
    }

    fun part1(input: List<String>, width: Int, height: Int): Int {

        val maxX = width
        val maxY = height

        var q1 = 0
        var q2 = 0
        var q3 = 0
        var q4 = 0

        input.robots()
            .forEach { robot: Robot ->
                var pos = robot.position
                repeat(100) {
                    pos += robot.vector
                    pos = Point((pos.x + maxX) % maxX, (pos.y + maxY) % maxY)
                }
                when {
                    pos.x < (maxX / 2) && pos.y < (maxY / 2) -> q1++
                    pos.x > (maxX / 2) && pos.y < (maxY / 2) -> q2++
                    pos.x < (maxX / 2) && pos.y > (maxY / 2) -> q3++
                    pos.x > (maxX / 2) && pos.y > (maxY / 2) -> q4++
                }
            }

        println("$q1 $q2 $q3 $q4")
        return q1 * q2 * q3 * q4
    }

    fun part2(input: List<String>): Int {

        val maxX = 101
        val maxY = 103

        var robots = input.robots()
        var i = 0

        val outputDir = File("output/day14")
        outputDir.mkdirs()

        repeat(10000) {

            val image = BufferedImage(101, 103, BufferedImage.TYPE_INT_RGB)

            for (x in 0 until 101) {
                for (y in 0 until 103) {
                    image.setRGB(
                        x,
                        y,
                        when {
                            robots.any { it.position == Point(x, y) } -> 0xffffff
                            else -> 0x000000
                        }
                    )
                }
            }
            ImageIO.write(image, "jpg", File(outputDir, "$i.jpg"))

            robots = robots.map {
                Robot(
                    position = Point(
                        x = (it.position.x + it.vector.x + maxX) % maxX,
                        y = (it.position.y + it.vector.y + maxY) % maxY
                    ),
                    vector = it.vector
                )
            }

            i++
        }
        return 0
    }

    val testInput = readInput("Day14_test")
    val input = readInput("Day14")

    test(12) { part1(testInput, 11, 7) }
    exec { part1(input, 101, 103) }
    exec { part2(input) }
}
