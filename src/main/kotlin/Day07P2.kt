import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.math.abs

fun main() {
    val input = Path("src/main/resources/Day07P2Input.txt").readText()
    val solutionValue = Day07P2().solve(input)
    println(solutionValue)
}

class Day07P2 {
    fun solve(input: String): Int {
        // Doing this to stop reduce from starting the accumulator at a higher value
        var crabPositions = mutableListOf(0)

        var crabPositionsFromInput = input.split(",").map { s -> Integer.parseInt(s) }
        crabPositions.addAll(crabPositionsFromInput)

        val maximumCrabPosition = crabPositions.maxOf { crabPosition -> crabPosition }

        return (0..maximumCrabPosition).minOf { positionToTest ->
            getFuelCost(crabPositions.toTypedArray(), positionToTest)
        }
    }

    fun getFuelCost(crabPositions: Array<Int>, alignmentIndex: Int): Int {
        return crabPositions.reduceIndexed { _, acc, crabPosition ->
            acc + (calculateFuelUsage(abs(crabPosition - alignmentIndex)))
        }
    }

    private fun calculateFuelUsage(differenceOfPosition: Int): Int {
        if(differenceOfPosition == 0) return 0
        return (1..differenceOfPosition).reduce { acc, currentIndex -> acc + currentIndex }
    }
}