package day02

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class Day02Test {

    private val moves = File("test/day02/input_test.txt").readLines().map(Day02::parseMove)

    @Test
    fun `part 1`() {
        Assertions.assertEquals(150, Day02.part1(moves))
    }

    @Test
    fun `part 2`() {
        Assertions.assertEquals(900, Day02.part2(moves))
    }
}