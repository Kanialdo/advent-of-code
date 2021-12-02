package day01

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class Day01Test {

    private val numbers = File("test/day01/input_test.txt").readLines().map(String::toInt)

    @Test
    fun `part 1`() {
        Assertions.assertEquals(7, Day01.part1(numbers))
    }

    @Test
    fun `part 1 alternative`() {
        Assertions.assertEquals(7, Day01.part1(numbers))
    }

    @Test
    fun `part 2`() {
        Assertions.assertEquals(5, Day01.part2(numbers))
    }

    @Test
    fun `part 2 alternative`() {
        Assertions.assertEquals(5, Day01.part2Alternative(numbers))
    }
}