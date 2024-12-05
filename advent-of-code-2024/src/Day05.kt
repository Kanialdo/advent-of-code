fun main() {

    fun part1(input: List<String>): Int {

        data class Order(val before: Int, val after: Int)

        val orders = input.filter { it.contains("|") }
            .map { it.split("|").map { it.toInt() }.let { Order(it[0], it[1]) } }
            .groupBy { it.before }
            .map { it.key to it.value.map { it.after } }
            .toMap()

        return input.filter { it.contains(",") }
            .map { it.split(",").map { it.toInt() } }
            .sumOf { sequence ->
                var isCorrect = true
                sequence.forEachIndexed { index, value ->
                    val valueOrders = orders.getOrDefault(value, emptyList())
                    isCorrect = isCorrect && sequence.subList(0, index).none { valueOrders.contains(it) }
                }
                if (isCorrect) sequence[sequence.size / 2] else 0
            }
    }

    fun part2(input: List<String>): Int {

        data class Order(val before: Int, val after: Int)

        val orders = input.filter { it.contains("|") }
            .map { it.split("|").map { it.toInt() }.let { Order(it[0], it[1]) } }

        data class Item(val value: Int) : Comparable<Item> {
            override fun compareTo(other: Item): Int {
                return when {
                    orders.filter { value == it.before }.any { it.after == other.value } -> -1
                    orders.filter { value == it.after }.any { it.before == other.value } -> 1
                    else -> 0
                }
            }
        }

        return input.filter { it.contains(",") }
            .map { it.split(",").map { it.toInt() } }
            .sumOf { sequence ->
                val sorted = sequence.map { Item(it) }.sorted().map { it.value }
                if (sequence.joinToString() != sorted.joinToString()) {
                    sorted[sorted.size / 2]
                } else {
                    0
                }
            }
    }

    val testInput = readInput("Day05_test")
    val input = readInput("Day05")

    test(143) { part1(testInput) }
    exec { part1(input) }
    test(123) { part2(testInput) }
    exec { part2(input) }
}
