import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val input = Path("src/main/resources/Day08Input.txt").readText().split("\n")
    val solutionValue = Day08P2().solve(input)
    println(solutionValue)
}

class Day08P2 {
    fun solve(inputLines: List<String>): Int {
        val outputStrings = inputLines.map { input ->
            val inputAndOutput = input.split("|")
                .map { s -> s.trim() }

            val input = inputAndOutput.first()
            val output = inputAndOutput.last()

            val inputToDigitMap = mapInputToDigit(input.split(" ").toMutableList())

            output.split(" ").joinToString(separator = "") { inputToDigitMap[it.toCharArray().sorted().toString()].toString() }
        }

        return outputStrings.sumOf { outputString -> Integer.parseInt(outputString) }
    }

    private fun mapInputToDigit(inputSignals: MutableList<String>): Map<String, Int> {
        var inputToDigitMap = HashMap<String, Int>()

        //Find the four digits we're sure of
        val oneInputSignal = inputSignals.find { i -> i.length == 2 }!!
        inputToDigitMap[oneInputSignal.toCharArray().sorted().toString()] = 1
        inputSignals.remove(oneInputSignal)

        val fourInputSignal = inputSignals.find { i -> i.length == 4 }!!
        inputToDigitMap[fourInputSignal.toCharArray().sorted().toString()] = 4
        inputSignals.remove(fourInputSignal)

        val sevenInputSignal = inputSignals.find { i -> i.length == 3 }!!
        inputToDigitMap[sevenInputSignal.toCharArray().sorted().toString()] = 7
        inputSignals.remove(sevenInputSignal)

        val eightInputSignal = inputSignals.find { i -> i.length == 7 }!!
        inputToDigitMap[eightInputSignal.toCharArray().sorted().toString()] = 8
        inputSignals.remove(eightInputSignal)

        //Next find 0, which will have length six and be missing one character from 8 and not include all the segments of 4
        val zeroInputSignal = inputSignals.find { inputSignal ->
            inputSignal.length == 6 &&
                    computeIntersection(eightInputSignal, inputSignal).size == 6 &&
                    computeIntersection(fourInputSignal, inputSignal).size == 3 &&
                    computeIntersection(oneInputSignal, inputSignal).size == 2
        }!!
        inputToDigitMap[zeroInputSignal.toCharArray().sorted().toString()] = 0
        inputSignals.remove(zeroInputSignal)

        //Next find 6, which is length six and compares to eight and one
        val sixInputSignal = inputSignals.find { inputSignal ->
            inputSignal.length == 6 &&
                    computeIntersection(eightInputSignal, inputSignal).size == 6 &&
                    computeIntersection(oneInputSignal, inputSignal).size == 1
        }!!
        inputToDigitMap[sixInputSignal.toCharArray().sorted().toString()] = 6
        inputSignals.remove(sixInputSignal)

        //Next find 9, which is the last remaining digit with length 6
        val nineInputSignal = inputSignals.find { inputSignal ->
            inputSignal.length == 6
        }!!
        inputToDigitMap[nineInputSignal.toCharArray().sorted().toString()] = 9
        inputSignals.remove(nineInputSignal)

        //Next find 5, which is length 5 and includes every part of 6
        val fiveInputSignal = inputSignals.find { inputSignal ->
            inputSignal.length == 5 &&
                    computeIntersection(sixInputSignal, inputSignal).size == 5
        }!!
        inputToDigitMap[fiveInputSignal.toCharArray().sorted().toString()] = 5
        inputSignals.remove(fiveInputSignal)

        //Next find 3 which has length 5 and includes every part of 7
        val threeInputSignal = inputSignals.find { inputSignal ->
            inputSignal.length == 5 &&
                    computeIntersection(sevenInputSignal, inputSignal).size == 3
        }!!
        inputToDigitMap[threeInputSignal.toCharArray().sorted().toString()] = 3
        inputSignals.remove(threeInputSignal)

        //Lastly we have 2
        val twoInputSignal = inputSignals.first()
        inputToDigitMap[twoInputSignal.toCharArray().sorted().toString()] = 2
        inputSignals.remove(twoInputSignal)

        return inputToDigitMap
    }

    private fun computeIntersection(
        targetDigitSignal: String,
        inputSignal: String
    ) = targetDigitSignal.toSet().intersect(inputSignal.toSet())

}