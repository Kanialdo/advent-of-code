import java.math.BigInteger
import kotlin.math.pow

// https://adventofcode.com/2024/day/7

fun pow2(num: Int) = 2.toDouble().pow(num).toInt()

data class Instruction(val result: BigInteger, val ingredients: List<BigInteger>) {

    companion object {

        fun parse(string: String): Instruction {
            val (result, ingredients) = string
                .split(":")
                .let { it[0].toBigInteger() to it[1].trim().split(" ").map { it.toBigInteger() } }
            return Instruction(result, ingredients)
        }
    }
}

fun main() {

    fun part1(input: List<String>): BigInteger {

        fun Instruction.getAvailableResults(): List<BigInteger> {

            val size = 2.toDouble().pow(ingredients.size - 1).toInt()
            val masks = List(size) { index -> index }

            return masks.map { mask ->
                ingredients.foldIndexed(0.toBigInteger()) { index, acc, value ->
                    when {
                        index == 0 -> value
                        pow2((index - 1)) and mask > 0 -> acc * value
                        else -> acc + value
                    }
                }
            }
        }

        return input.map { Instruction.parse(it) }
            .sumOf { instruction ->
                println(
                    "${if (instruction.result in instruction.getAvailableResults()) "✅" else "☑️"} ${instruction.result}"
                )
                instruction.result.takeIf { instruction.result in instruction.getAvailableResults() }
                    ?: 0.toBigInteger()
            }
    }

    fun part2(input: List<String>): BigInteger {

        fun Instruction.getAvailableResults(): List<BigInteger> {

            val size = 3.toDouble().pow(ingredients.size).toLong()
            val masks = mutableListOf<String>()
            var i = 0.toLong()
            while (i < size) {
                masks.add(i.toString(3).padStart(16,'0').reversed())
                i++
            }

            return masks.map { mask ->
                ingredients.foldIndexed(0.toBigInteger()) { index, acc, value ->
                    when {
                        index == 0 -> value
                        mask.getOrNull(index - 1) == '2' -> acc * value
                        mask.getOrNull(index - 1) == '1' -> acc + value
                        else -> (acc.toString() + value.toString()).toBigInteger()
                    }
                }
            }
        }

        return input.map { Instruction.parse(it) }
            .sumOf { instruction ->
                val availableResults = instruction.getAvailableResults()
                println(
                    "${if (instruction.result in availableResults) "✅" else "☑️"} ${instruction.result}"
                )
                instruction.result.takeIf { instruction.result in availableResults }
                    ?: 0.toBigInteger()
            }

    }

    val testInput = readInput("Day07_test")
    val input = readInput("Day07")

    testBigInteger(3749.toBigInteger()) { part1(testInput) }
    execBigInteger { part1(input) }

    testBigInteger(11387.toBigInteger()) { part2(testInput) }
    execBigInteger { part2(input) }
}
