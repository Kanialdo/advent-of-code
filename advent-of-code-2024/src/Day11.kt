import java.math.BigInteger

// https://adventofcode.com/2024/day/11

fun main() {


    data class Cache(private val cache: MutableMap<Pair<Long, Int>, BigInteger> = mutableMapOf()) {
        fun getCache(number: Long, deep: Int): BigInteger? = cache[number to deep]
        fun saveCache(number: Long, deep: Int, result: BigInteger) {
            cache[number to deep] = result
        }
    }

    fun dig(cache: Cache, maxLevel: Int, level: Int, number: Long): BigInteger {
        val cachedResult = cache.getCache(number, maxLevel - level)
        return when {
            level == maxLevel -> 1.toBigInteger()
            cachedResult != null -> cachedResult
            number == 0.toLong() -> dig(cache, maxLevel, level + 1, 1)
            number.toString().length % 2 == 1 -> dig(cache, maxLevel, level + 1, number * 2024)
            else -> {
                val (n1, n2) = number.toString()
                    .let {
                        it.substring(0, it.length / 2).toLong() to it.substring(it.length / 2).toLong()
                    }
                dig(cache, maxLevel, level + 1, n1) + dig(cache, maxLevel, level + 1, n2)
            }
        }.apply {
            cache.saveCache(number, maxLevel - level, this)
        }
    }

    fun part1(input: List<String>): BigInteger {
        return input.first()
            .split(" ")
            .map { it.toLong() }
            .sumOf { dig(Cache(), 25, 0, it) }
    }

    fun part2(input: List<String>): BigInteger {
        return input.first()
            .split(" ")
            .map { it.toLong() }
            .sumOf { dig(Cache(), 75, 0, it) }
    }

    val testInput = readInput("Day11_test")
    val input = readInput("Day11")

    testBigInteger(55312.toBigInteger()) { part1(testInput) }
    execBigInteger { part1(input) }
    execBigInteger { part2(input) }
}
