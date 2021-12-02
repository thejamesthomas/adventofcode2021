import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val inputLines = Path("src/main/resources/Day01Input.txt").readText().split("\n")
    val solutionValue = Day01().solve(inputLines)
    println(solutionValue)
}

class Day01 {
    fun solve(inputLines: List<String>): Int {
        val inputInts = inputLines.map { Integer.parseInt(it) }

        var numberOfHigherSubsequentMeasurements = 0
        inputInts.forEachIndexed { i, current ->
            println(i + 1)
            if(i + 1 < inputInts.size)
                if(current < inputInts[i + 1])
                    numberOfHigherSubsequentMeasurements++
        }
        return numberOfHigherSubsequentMeasurements
    }
}