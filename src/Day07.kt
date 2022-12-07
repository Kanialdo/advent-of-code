import kotlin.math.min

fun main() {

    data class Node(
        val name: String,
        val parent: Node?,
        val children: MutableList<Node> = mutableListOf(),
        var size: Int = 0,
        val isFile: Boolean = false,
    )

    fun Node.print(index: Int = 0) {
        print("-".padStart(index * 2, ' ') + " " + name)
        if (size > 0) {
            print(" $size")
        }
        println()
        children.forEach { it.print(index + 1) }
    }

    fun Node.updateSize() {
        children.forEach {
            it.updateSize()
        }
        this.size += children.sumOf { it.size }
    }

    fun Node.sum(): Int {
        return children.sumOf { it.sum() } + if (!isFile && size <= 100000) size else 0
    }

    fun Node.findClosed(value: Int, current: Int = Int.MAX_VALUE): Int {
        return min(children.minOfOrNull { it.findClosed(value, current) } ?: Int.MAX_VALUE,
            if (!isFile && size > value) size else Int.MAX_VALUE)
    }


    fun buildTree(input: List<String>): Node {
        val root = Node(name = "/", parent = null)

        var currentNode = root

        input.drop(1).forEach { line ->
            when {
                line.startsWith("$ cd ..") -> currentNode = currentNode.parent ?: error("No parent")
                line.startsWith("$ cd") -> currentNode = currentNode.children.first { it.name == line.drop(5) }
                line.startsWith("$ ls") -> Unit
                line.startsWith("dir") -> currentNode.children.add(Node(name = line.drop(4), parent = currentNode))
                else -> {
                    val (size, name) = line.split(" ")
                    currentNode.children.add(
                        Node(name = name, parent = currentNode, size = size.toInt(), isFile = true)
                    )
                }
            }
        }

        root.updateSize()
        return root
    }

    fun part1(input: List<String>): Int {
        val root = buildTree(input)
        root.print()
        return root.sum()
    }

    fun part2(input: List<String>): Int {
        val root = buildTree(input)

        val totalSize = 70000000
        val requiredSpace = 30000000

        val occupiedSpace = root.size
        val freeSpace = totalSize - occupiedSpace

        val missingSpace = requiredSpace - freeSpace

        return root.findClosed(missingSpace)
    }

    val testInput = readInput("Day07_test")
    check(part1(testInput), 95437)
    check(part2(testInput), 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}