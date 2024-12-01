import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.time.measureTime

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readText().trim().lines()

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

fun exec(command: () -> Int) {
    val result: Int
    val time = measureTime {
        result = command()
    }

    println("Result $result in ${time.inWholeMicroseconds} µs")
}