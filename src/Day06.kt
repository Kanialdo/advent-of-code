fun main() {

    fun part1(input: String): Int {
        return input.windowed(4).indexOfFirst { chars -> chars.toSet().size == 4 } + 4
    }

    fun part2(input: String): Int {
        return input.windowed(14).indexOfFirst { chars -> chars.toSet().size == 14 } + 14
    }

    check(part1("mjqjpqmgbljsphdztnvjfqwrcgsmlb") == 7)
    check(part1("bvwbjplbgvbhsrlpgdmjqwftvncz") == 5)
    check(part1("nppdvjthqldpwncqszvftbrmjlhg") == 6)
    check(part1("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") == 10)
    check(part1("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") == 11)

    check(part2("mjqjpqmgbljsphdztnvjfqwrcgsmlb") == 19)
    check(part2("bvwbjplbgvbhsrlpgdmjqwftvncz") == 23)
    check(part2("nppdvjthqldpwncqszvftbrmjlhg") == 23)
    check(part2("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") == 29)
    check(part2("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") == 26)

    val input = readInput("Day06").first()
    println(part1(input))
    println(part2(input))
}