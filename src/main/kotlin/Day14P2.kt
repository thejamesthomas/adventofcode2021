import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val input = Path("src/main/resources/Day14Input.txt").readText().split("\n")
    val solutionValue = Day14P2(40).solve(input)
    println(solutionValue)
}

class Day14P2(val numberOfStepsToSimulate: Int) {
    fun solve(inputLines: List<String>): Int {
        var inputFile = File("Day14P2.txt")
        var insertionRules = HashMap<String, Char>()
        inputFile.writeText(inputLines.first())

        inputLines.slice(2 until inputLines.size).map { line ->
            val (pair, insertion) = line.split(" -> ")
            insertionRules.put(pair, insertion[0])
        }

        for (step in 1..numberOfStepsToSimulate) {
            val stepOutputFile = File(String.format("Day14P2step%2d.txt", step))
            val outputWriter = stepOutputFile.writer()
            val reader = inputFile.bufferedReader()


            var isFirst = true
            while (true) {
                val firstCharacter = reader.read().toChar()
                reader.mark(1)
                val secondCharacter = reader.read().toChar()
                if (firstCharacter == '\uFFFF' || secondCharacter == '\uFFFF') {
                    break
                }
                reader.reset()
                val pair = firstCharacter.toString() + secondCharacter.toString()
                val toInsert = insertionRules[pair]

                if(isFirst) {
                    isFirst = false
                    outputWriter.write(String.format("%s%s%s", firstCharacter, toInsert!!, secondCharacter))
                } else {
                    outputWriter.write(String.format("%s%s", toInsert!!, secondCharacter))
                }
            }
            outputWriter.flush()
            inputFile = stepOutputFile
        }


        var elementCounts = HashMap<Char, Int>()
        val reader = inputFile.bufferedReader()
        while (true) {
            val element = reader.read().toChar()
            if (element == '\uFFFF')
                break
            if (elementCounts[element] != null) {
                elementCounts[element] = elementCounts[element]!! + 1
            } else {
                elementCounts[element] = 1
            }
        }

        val sortedElementCounts = elementCounts.toList().sortedByDescending { (_, value) -> value }

        return sortedElementCounts.first().second - sortedElementCounts.last().second
    }
}