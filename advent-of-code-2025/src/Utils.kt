import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.time.measureTime

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String, trim: Boolean = true) = Path("src/$name.txt")
    .readText()
    .let { if (trim) it.trim() else it }
    .lines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)


fun test(expected: Int, command: () -> Int) {
    val result: Int
    val time = measureTime {
        result = command()
    }

    if (expected == result) {
        println("Test passed in ${time.inWholeMicroseconds} µs")
    } else {
        error("Test failed. Expected $expected, but was $result")
    }
}

fun testLong(expected: Long, command: () -> Long) {
    val result: Long
    val time = measureTime {
        result = command()
    }

    if (expected == result) {
        println("Test passed in ${time.inWholeMicroseconds} µs")
    } else {
        error("Test failed. Expected $expected, but was $result")
    }
}

fun testDouble(expected: Double, command: () -> Double) {
    val result: Double
    val time = measureTime {
        result = command()
    }

    if (expected == result) {
        println("Test passed in ${time.inWholeMicroseconds} µs")
    } else {
        error("Test failed. Expected $expected, but was $result")
    }
}

fun testBigInteger(expected: BigInteger, command: () -> BigInteger) {
    val result: BigInteger
    val time = measureTime {
        result = command()
    }

    if (expected == result) {
        println("Test passed in ${time.inWholeMicroseconds} µs")
    } else {
        error("Test failed. Expected $expected, but was $result")
    }
}

fun exec(command: () -> Int) {
    val result: Int
    val time = measureTime {
        result = command()
    }

    println("Result $result in ${time.inWholeMicroseconds} µs")
}

fun execLong(command: () -> Long) {
    val result: Long
    val time = measureTime {
        result = command()
    }

    println("Result $result in ${time.inWholeMicroseconds} µs")
}

fun execDouble(command: () -> Double) {
    val result: Double
    val time = measureTime {
        result = command()
    }

    println("Result $result in ${time.inWholeMicroseconds} µs")
}

fun execBigInteger(command: () -> BigInteger) {
    val result: BigInteger
    val time = measureTime {
        result = command()
    }

    println("Result $result in ${time.inWholeMicroseconds} µs")
}