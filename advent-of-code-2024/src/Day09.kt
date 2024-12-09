import java.math.BigInteger

// https://adventofcode.com/2024/day/9

fun main() {

    fun part1(input: List<String>): BigInteger {
        val line = input.first()

        val disk = mutableListOf<Int>()
        line.map { it.digitToInt() }
            .forEachIndexed { index, number ->
                when {
                    index % 2 == 0 -> for (i in 0 until number) disk.add(index / 2)
                    else -> for (i in 0 until number) disk.add(-1)
                }
            }

        // println(disk.joinToString("") { if (it == -1) "." else it.toString() })

        do {
            val number = disk.last()
            val index = disk.indexOfFirst { it == -1 }
            disk[index] = number
            disk.removeLast()
            while (disk[disk.size - 1] == -1) {
                disk.removeLast()
            }
        } while (disk.any { it == -1 })

        // println(disk.joinToString("") { if (it == -1) "." else it.toString() })

        return disk.foldIndexed(0.toBigInteger()) { index, acc, i -> acc + index.toBigInteger() * i.toBigInteger() }
    }

    fun part2(input: List<String>): Int {
        return TODO()
    }

    val testInput = readInput("Day09_test")
    val input = readInput("Day09")

    testBigInteger(1928.toBigInteger()) { part1(testInput) }
    execBigInteger { part1(input) }
    test(TODO()) { part2(testInput) }
    exec { part2(input) }
}
