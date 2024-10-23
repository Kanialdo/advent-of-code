import kotlin.math.min

fun main() {

    class Node(
        val name: String,
        val parent: Node?,
        val children: MutableList<Node> = mutableListOf(),
        var size: Int = 0,
    ) {
        fun print(index: Int = 0) {
            println("-".padStart(index * 2, ' ') + " " + name)
            children.forEach { it.print(index + 1) }
        }
    }

    fun buildTree(input: List<String>): Node {
        val root = Node(name = "/", parent = null)

        var currentNode = root

        input.drop(1).forEach { line ->
            when {
                line.startsWith("$ cd ..") -> currentNode = currentNode.parent ?: error("No parent")
                line.startsWith("$ cd") -> {
                    val dir = line.drop(5)
                    currentNode = currentNode.children.first { it.name == dir }
                }
                line.startsWith("$ ls") -> Unit
                line.startsWith("dir") -> {
                    val dir = line.drop(4)
                    currentNode.children.add(Node(name = dir, parent = currentNode))
                }
                else -> {
                    currentNode.size += line.split(" ").first().toInt()
                }
            }
        }

        fun Node.updateSize() {
            children.forEach {
                it.updateSize()
            }
            this.size += children.sumOf { it.size }
        }

        root.updateSize()
        return root
    }

    fun part1(input: List<String>): Int {
        val root = buildTree(input)
        root.print()

        fun Node.sum(): Int {
            return children.sumOf { it.sum() } + if (size <= 100000) size else 0
        }

        return root.sum()
    }

    fun part2(input: List<String>): Int {
        val root = buildTree(input)
        val lookingSpace = 30000000 - (70000000 - root.size)

        fun Node.findClosed(threshold: Int): Int {
            return min(
                a = children.minOfOrNull { it.findClosed(threshold) } ?: Int.MAX_VALUE,
                b = if (size > threshold) size else Int.MAX_VALUE
            )
        }

        return root.findClosed(lookingSpace)
    }

    val testInput = readInput("Day07_test")
    check(part1(testInput), 95437)
    check(part2(testInput), 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}