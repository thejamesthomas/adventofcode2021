import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val input = Path("src/main/resources/Day14Input.txt").readText().split("\n")
    val solutionValue = Day14P2(40).solve(input)
    println(solutionValue)
}

class Day14P2(val numberOfStepsToSimulate: Int) {
    fun solve(inputLines: List<String>): Long {
        var insertionRules = HashMap<String, Char>()
        var elementCounts = HashMap<Char, Long>()

        var generator = HashMap<String, Long>()
        var generatorIncrement = HashMap<String, Long>()

        inputLines.slice(2 until inputLines.size).map { line ->
            val (pair, insertion) = line.split(" -> ")
            insertionRules[pair] = insertion[0]
            generator[pair] = 0
            generatorIncrement[pair] = 0
            elementCounts.put(insertion[0], 0)
        }
        val firstLine = inputLines.first()
        firstLine.forEach { c -> elementCounts[c] = elementCounts[c]!! + 1 }

        firstLine.windowed(2).forEach { pair ->
            generator[pair] = generator[pair]!! + 1
        }

        for (step in 1..numberOfStepsToSimulate) {
            generator
                .filter { entry -> entry.value > 0 }
                .forEach { entry ->
                    val polymerPair = entry.key
                    val timesPairAppears = entry.value
                    val newPolymerToInsert = insertionRules[polymerPair]!!
                    val firstNewPolymer = polymerPair[0] + newPolymerToInsert.toString()
                    val secondNewPolymer = newPolymerToInsert.toString() + polymerPair[1]

                    generatorIncrement[firstNewPolymer] = generatorIncrement[firstNewPolymer]!! + timesPairAppears
                    generatorIncrement[secondNewPolymer] = generatorIncrement[secondNewPolymer]!! + timesPairAppears
                    generatorIncrement[polymerPair] = generatorIncrement[polymerPair]!! - timesPairAppears
                    elementCounts[newPolymerToInsert] = elementCounts[newPolymerToInsert]!! + timesPairAppears
                }

            generatorIncrement.forEach { entry ->
                generator[entry.key] = generator[entry.key]!! + entry.value
            }

            generatorIncrement.keys.forEach { key ->
                generatorIncrement[key] = 0
            }
        }
        val sortedElementCounts = elementCounts.toList().sortedByDescending { (_, value) -> value }

        return sortedElementCounts.first().second - sortedElementCounts.last().second
    }
}