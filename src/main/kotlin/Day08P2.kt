import java.util.*
import kotlin.collections.HashMap
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
                .map { fullInput -> fullInput.trim() }
                .map { trimmedInput -> trimmedInput.split(" ")
                    .map { inputSignal -> inputSignal.toSortedSet() }
                }

            val input = inputAndOutput.first().toMutableList()
            val output = inputAndOutput.last()

            val inputToDigitMap = mapInputToDigit(input)

            output.joinToString(separator = "") { inputToDigitMap[it].toString() }
        }

        return outputStrings.sumOf { outputString -> Integer.parseInt(outputString) }
    }

    private fun mapInputToDigit(inputSignals: MutableList<SortedSet<Char>>): Map<SortedSet<Char>, Int> {
        var inputToDigitMap = HashMap<SortedSet<Char>, Int>()

        //Find the four digits we're sure of
        val oneInputSignal = inputSignals.find { i -> i.size == 2 }!!
        inputToDigitMap[oneInputSignal] = 1
        inputSignals.remove(oneInputSignal)

        val fourInputSignal = inputSignals.find { i -> i.size == 4 }!!
        inputToDigitMap[fourInputSignal] = 4
        inputSignals.remove(fourInputSignal)

        val sevenInputSignal = inputSignals.find { i -> i.size == 3 }!!
        inputToDigitMap[sevenInputSignal] = 7
        inputSignals.remove(sevenInputSignal)

        val eightInputSignal = inputSignals.find { i -> i.size == 7 }!!
        inputToDigitMap[eightInputSignal] = 8
        inputSignals.remove(eightInputSignal)

        //Next find 0, which will have length six and be missing one character from 8 and not include all the segments of 4
        val zeroInputSignal = inputSignals.find { inputSignal ->
            inputSignal.size == 6 &&
                    eightInputSignal.intersect(inputSignal).size == 6 &&
                    fourInputSignal.intersect(inputSignal).size == 3 &&
                    oneInputSignal.intersect(inputSignal).size == 2
        }!!
        inputToDigitMap[zeroInputSignal] = 0
        inputSignals.remove(zeroInputSignal)

        //Next find 6, which is length six and compares to eight and one
        val sixInputSignal = inputSignals.find { inputSignal ->
            inputSignal.size == 6 &&
                    eightInputSignal.intersect(inputSignal).size == 6 &&
                    oneInputSignal.intersect(inputSignal).size == 1
        }!!
        inputToDigitMap[sixInputSignal] = 6
        inputSignals.remove(sixInputSignal)

        //Next find 9, which is the last remaining digit with length 6
        val nineInputSignal = inputSignals.find { inputSignal ->
            inputSignal.size == 6
        }!!
        inputToDigitMap[nineInputSignal] = 9
        inputSignals.remove(nineInputSignal)

        //Next find 5, which is length 5 and includes every part of 6
        val fiveInputSignal = inputSignals.find { inputSignal ->
            inputSignal.size == 5 &&
                    sixInputSignal.intersect(inputSignal).size == 5
        }!!
        inputToDigitMap[fiveInputSignal] = 5
        inputSignals.remove(fiveInputSignal)

        //Next find 3 which has length 5 and includes every part of 7
        val threeInputSignal = inputSignals.find { inputSignal ->
            inputSignal.size == 5 &&
                    sevenInputSignal.intersect(inputSignal).size == 3
        }!!
        inputToDigitMap[threeInputSignal] = 3
        inputSignals.remove(threeInputSignal)

        //Lastly we have 2
        val twoInputSignal = inputSignals.first()
        inputToDigitMap[twoInputSignal] = 2
        inputSignals.remove(twoInputSignal)

        return inputToDigitMap
    }
}