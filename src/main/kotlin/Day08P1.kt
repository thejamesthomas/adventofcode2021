import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val input = Path("src/main/resources/Day08Input.txt").readText().split("\n")
    val solutionValue = Day08P1().solve(input)
    println(solutionValue)
}

class Day08P1 {
    fun solve(inputLines: List<String>): Int {
        val numberOfOnes = inputLines.sumOf { input ->
            input.split("|")[1].split(" ").count { sequence -> sequence.length == 2 }
        }
        val numberOfFours = inputLines.sumOf { input ->
            input.split("|")[1].split(" ").count { sequence -> sequence.length == 4 }
        }
        val numberOfSevens = inputLines.sumOf { input ->
            input.split("|")[1].split(" ").count { sequence -> sequence.length == 3 }
        }
        val numberOfEights = inputLines.sumOf { input ->
            input.split("|")[1].split(" ").count { sequence -> sequence.length == 7 }
        }
        return numberOfOnes + numberOfFours + numberOfSevens + numberOfEights
    }
}