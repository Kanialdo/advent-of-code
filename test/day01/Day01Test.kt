package day01

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class Day01Test {

    @Test
    fun `part 1`() {
        val numbers = File("test/day01/input_test.txt").readLines().map(String::toInt)
        val result = Day01.part1(numbers)
        Assertions.assertEquals(7, result)
    }

    @Test
    fun `part 2`() {
        val numbers = File("test/day01/input_test.txt").readLines().map(String::toInt)
        val result = Day01.part2(numbers)
        Assertions.assertEquals(5, result)
    }
}