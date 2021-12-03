import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val inputLines = Path("src/main/resources/Day03P2Input.txt").readText().split("\n")
    val solutionValue = Day03P2().solve(inputLines)
    println(solutionValue)
}

private const val ASCII_CHARACTER_ZERO_OFFSET_VALUE = 48

class Day03P2 {
    data class Rate(
        val oxygenGenerator: Int, val co2Scrubber: Int
    )

    fun solve(inputLines: List<String>): Rate {

        var filteredInput: List<String> = inputLines
        for (i in inputLines.indices) {
            val mostCommonDigit = filteredInput.getMostCommonDigitForIndex(i)
            filteredInput = if (mostCommonDigit == -1) {
                filteredInput.filter { line -> line[i].convertToInt() == 1 }
            } else {
                filteredInput.filter { line ->
                    line[i].convertToInt() == mostCommonDigit
                }
            }
            if (filteredInput.size == 1) break
        }
        val oxygenGeneratorBinary = filteredInput.first()

        filteredInput = inputLines
        for (i in inputLines.indices) {
            val leastCommonDigit = filteredInput.getLeastCommonDigitForIndex(i)
            filteredInput = if (leastCommonDigit == -1) {
                filteredInput.filter { line -> line[i].convertToInt() == 0 }
            } else {
                filteredInput.filter { line ->
                    line[i].convertToInt() == leastCommonDigit
                }
            }
            if (filteredInput.size == 1) break
        }
        val co2ScrubberBinary = filteredInput.first()

        val oxygenGeneratorDecimal = Integer.parseInt(oxygenGeneratorBinary, 2)
        val co2ScrubberDecimal = Integer.parseInt(co2ScrubberBinary, 2)

        return Rate(oxygenGeneratorDecimal, co2ScrubberDecimal)
    }
}

fun List<String>.getMostCommonDigitForIndex(index: Int): Int {
    val countOfZeroes = this.count { s -> s[index].convertToInt() == 0 }
    val countOfOnes = this.count { s -> s[index].convertToInt() == 1 }

    return if (countOfZeroes == countOfOnes) -1 else if (countOfZeroes > countOfOnes) 0 else 1
}

fun List<String>.getLeastCommonDigitForIndex(index: Int): Int {
    val mostCommonDigitForIndex = getMostCommonDigitForIndex(index)
    return if (mostCommonDigitForIndex == -1) -1 else if (mostCommonDigitForIndex == 1) 0 else 1
}

private fun Char.convertToInt() = this.code - ASCII_CHARACTER_ZERO_OFFSET_VALUE