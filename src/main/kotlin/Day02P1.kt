import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val inputLines = Path("src/main/resources/Day02P1Input.txt").readText().split("\n")
    val solutionValue = Day02P1().solve(inputLines)
    println(solutionValue)
}

class Day02P1 {
    fun solve(inputLines: List<String>): Pair<Int, Int> {
        var depth: Int
        var horizontal: Int

        val forward = extractCommandSum(inputLines, "forward")
        val down = extractCommandSum(inputLines, "down")
        val up = extractCommandSum(inputLines, "up")

        depth = down - up
        horizontal = forward

        return Pair(depth, horizontal)
    }

    private fun extractCommandSum(inputLines: List<String>, command: String): Int {
        return inputLines.filter { s -> s.contains(command) }.sumOf { s -> Integer.parseInt(s.split(" ")[1]) }
    }
}