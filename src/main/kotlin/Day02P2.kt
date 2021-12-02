import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val inputLines = Path("src/main/resources/Day02P2Input.txt").readText().split("\n")
    val solutionValue = Day02P2().solve(inputLines)
    println(solutionValue)
}

class Day02P2 {
    data class Position(
        val depth: Int,
        val horizontal: Int
    ) {
        override fun toString(): String = "($depth, $horizontal)"
    }

    fun solve(commands: List<String>): Position {
        var aim = 0
        var depth = 0
        var horizontal = 0

        commands.forEach { command ->
            if (command.contains("forward")) {
                val commandValue = extractCommandValue(command)
                horizontal += commandValue
                depth += aim * commandValue
            } else {
                val commandValue = extractCommandValue(command)
                aim += (commandValue * (if (command.contains("down")) 1 else -1))
            }
        }

        return Position(depth, horizontal)
    }

    private fun extractCommandValue(command: String) = Integer.parseInt(command.split(" ")[1])
}