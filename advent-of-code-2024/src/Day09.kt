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

    fun part2(input: List<String>): BigInteger {
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

        var lastNumber = disk.last()
        do {
            val size = disk.count { it == lastNumber }
            var dotCounter = 0
            for (i in 0 until disk.indexOfFirst { it == lastNumber }) {
                if (disk[i] == -1) {
                    dotCounter++
                } else {
                    dotCounter = 0
                }
                if (dotCounter == size) {
                    for (j in disk.indexOfFirst { it == lastNumber }..disk.indexOfLast { it == lastNumber }) {
                        disk[j] = -1
                    }
                    for (j in 0 until dotCounter) {
                        disk[i - j] = lastNumber
                    }
                    break
                }
            }
            lastNumber--

        } while (lastNumber >= 0)

        return disk.foldIndexed(0.toBigInteger()) { index, acc, i ->
            when {
                i == -1 -> acc
                else -> acc + index.toBigInteger() * i.toBigInteger()
            }
        }
    }


    val testInput = readInput("Day09_test")
    val input = readInput("Day09")

    testBigInteger(1928.toBigInteger()) { part1(testInput) }
    execBigInteger { part1(input) }
    testBigInteger(2858.toBigInteger()) { part2(testInput) }
    execBigInteger { part2(input) }
}
