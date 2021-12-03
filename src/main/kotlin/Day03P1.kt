import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val inputLines = Path("src/main/resources/Day03P1Input.txt").readText().split("\n")
    val solutionValue = Day03P1().solve(inputLines)
    println(solutionValue)
}

class Day03P1 {
    data class Rate(
        val gamma: Int,
        val epsilon: Int
    )

    fun solve(inputLines: List<String>): Rate {
        var digitsCounter = IntArray(inputLines.first().length)
        inputLines.forEach { line ->
            line.forEachIndexed { i, character ->
                digitsCounter[i] += ( character.code - 48 )
            }
        }

        var gammaBinary = IntArray(digitsCounter.size)
        digitsCounter.forEachIndexed { i, digit ->
            gammaBinary[i] = digitsCounter[i] / (inputLines.size / 2)
        }

        var epsilonBinary = IntArray(digitsCounter.size)
        epsilonBinary.forEachIndexed { i, digit ->
            epsilonBinary[i] = if(gammaBinary[i] == 0) 1 else 0
        }

        val gammaDecimal = Integer.parseInt(gammaBinary.joinToString(""), 2)
        val epsilonDecimal = Integer.parseInt(epsilonBinary.joinToString(""), 2)
        return Rate(gammaDecimal, epsilonDecimal)
    }
}