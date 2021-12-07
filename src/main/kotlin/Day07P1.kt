import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.math.abs

fun main() {
    val input = Path("src/main/resources/Day07P1Input.txt").readText()
    val solutionValue = Day07P1().solve(input)
    println(solutionValue)
}

class Day07P1 {
    fun solve(input: String): Int {
        // Doing this to stop reduce from starting the accumulator at a higher value
        var crabPositions = mutableListOf(0)

        var crabPositionsFromInput = input.split(",").map { s -> Integer.parseInt(s) }
        crabPositions.addAll(crabPositionsFromInput)

        val maximumPosition = crabPositions.maxOf { i -> i }

        return (0..maximumPosition).minOf {
            getFuelCost(crabPositions.toTypedArray(), it)
        }
    }

    fun getFuelCost(crabPositions: Array<Int>, alignmentIndex: Int): Int {
        return crabPositions.reduceIndexed { index, acc, crabPosition ->
            acc + abs((crabPosition - alignmentIndex))
        }
    }
}