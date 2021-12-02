import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val inputLines = Path("src/main/resources/Day02P1Input.txt").readText().split("\n")
    val solutionValue = Day02P1().solve(inputLines)
    println(solutionValue)
}

class Day02P1 {
    data class Position(
        val depth: Int,
        val horizontal: Int
    ) {
        override fun toString(): String = "($depth, $horizontal)"
    }

    fun solve(inputLines: List<String>): Position {
        var depth: Int
        var horizontal: Int

        val forward = extractCommandSum(inputLines, "forward")
        val down = extractCommandSum(inputLines, "down")
        val up = extractCommandSum(inputLines, "up")

        depth = down - up
        horizontal = forward

        return Position(depth, horizontal)
    }

    private fun extractCommandSum(inputLines: List<String>, command: String): Int {
        return inputLines.filter { s -> s.contains(command) }.sumOf { s -> Integer.parseInt(s.split(" ")[1]) }
    }
}