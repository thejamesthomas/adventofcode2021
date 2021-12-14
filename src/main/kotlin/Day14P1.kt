import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val input = Path("src/main/resources/Day14Input.txt").readText().split("\n")
    val solutionValue = Day14P1(10).solve(input)
    println(solutionValue)
}

class Day14P1(val numberOfStepsToSimulate: Int) {
    fun solve(inputLines: List<String>): Int {
        var insertionRules = HashMap<String,String>()
        var polymerString = inputLines.first()
        inputLines.slice(2 until inputLines.size).map { line ->
            val (pair, insertion) = line.split(" -> ")
            insertionRules.put(pair, insertion)
        }

        var polymerStringBuilder = StringBuilder(polymerString)
        for (step in 1..numberOfStepsToSimulate) {
            polymerString = polymerStringBuilder.toString()
            polymerStringBuilder = StringBuilder()
            var isFirst = true
            polymerString.windowed(2).forEach { pair ->
                if(isFirst) {
                    polymerStringBuilder.append(pair.first())
                    isFirst = false
                }
                polymerStringBuilder.append(insertionRules[pair], pair.last())

            }
            println("After Step $step:" + polymerStringBuilder.toString())
        }
        polymerString = polymerStringBuilder.toString()

        var elementCounts = HashMap<Char, Int>()
        polymerString.forEach { element ->
            if(elementCounts[element] != null) {
                elementCounts[element] = elementCounts[element]!! + 1
            } else {
                elementCounts[element] = 1
            }
        }
        val sortedElementCounts = elementCounts.toList().sortedByDescending { (_, value) -> value }

        return sortedElementCounts.first().second - sortedElementCounts.last().second
    }
}
