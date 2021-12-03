package utils

fun <T> verify(expected: T, result: T) = check(expected == result) {
    "Expected $expected, but received $result"
}