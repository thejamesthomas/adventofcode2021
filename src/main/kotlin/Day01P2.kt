import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val inputLines = Path("src/main/resources/Day01P2Input.txt").readText().split("\n")
    val solutionValue = Day01P2().solve(inputLines)
    println(solutionValue)
}

class Day01P2 {
    fun solve(inputLines: List<String>): Int {
        val inputInts = inputLines.map { Integer.parseInt(it) }

        val calculatedWindows =
            inputInts
                .mapIndexed { i, currentValue -> currentValue + inputInts.getOrElse(i + 1) { 0 } + inputInts.getOrElse(i + 2) { 0 } }
                .dropLast(2) //to account for the last two measurements not including three full values

        var numberOfHigherSubsequentMeasurements = 0
        calculatedWindows.forEachIndexed { i, current ->
            if(i + 1 < calculatedWindows.size)
                if(current < calculatedWindows[i + 1])
                    numberOfHigherSubsequentMeasurements++
        }
        return numberOfHigherSubsequentMeasurements
    }
}