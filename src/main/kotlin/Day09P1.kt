import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val input = Path("src/main/resources/Day09Input.txt").readText().split("\n")
    val solutionValue = Day09P1().solve(input)
    println(solutionValue)
}

class Day09P1 {
    fun solve(inputLines: List<String>): Int {
        val rowsOfMeasurements = inputLines.map { line ->
            line.toList().map { character -> character.digitToInt() }
        }

        var riskLevel = 0
        rowsOfMeasurements.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { measurementIndex, currentMeasurement ->
                val measurementToTheRight: Int = try {
                    row[measurementIndex + 1]
                } catch (e: Exception) {
                    10
                }
                val measurementToTheLeft: Int = try {
                    row[measurementIndex - 1]
                } catch (e: Exception) {
                    10
                }
                val measurementAbove: Int = try {
                    rowsOfMeasurements[rowIndex - 1][measurementIndex]
                } catch (e: Exception) {
                    10
                }
                val measurementBelow: Int = try {
                    rowsOfMeasurements[rowIndex + 1][measurementIndex]
                } catch (e: Exception) {
                    10
                }

                val isLowPoint = listOf(
                    measurementToTheRight,
                    measurementToTheLeft,
                    measurementAbove,
                    measurementBelow
                ).all { measurementToCompare -> measurementToCompare > currentMeasurement }

                if(isLowPoint) {
//                    println(String.format("[%d,%d] is a low point", measurementIndex, rowIndex))
//                    println(String.format("   %3d   ", measurementAbove))
//                    println(String.format("%3d%3d%3d", measurementToTheLeft, currentMeasurement, measurementToTheRight))
//                    println(String.format("   %3d   ", measurementBelow))
//                    println()
                    riskLevel += currentMeasurement + 1
                } else {
//                    println(String.format("[%d,%d] is NOT a low point", measurementIndex, rowIndex))
//                    println(String.format("   %3d   ", measurementAbove))
//                    println(String.format("%3d%3d%3d", measurementToTheLeft, currentMeasurement, measurementToTheRight))
//                    println(String.format("   %3d   ", measurementBelow))
//                    println()
                }
            }
        }
        return riskLevel
    }
}