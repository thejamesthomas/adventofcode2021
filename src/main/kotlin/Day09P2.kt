import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val input = Path("src/main/resources/Day09Input.txt").readText().split("\n")
    val solutionValue = Day09P2().solve(input)
    println(solutionValue)
}

class Day09P2 {
    fun solve(inputLines: List<String>): Int {
        val rowsOfMeasurements = inputLines.map { line ->
            line.toList().map { character -> character.digitToInt() }
        }

        var basinPaths = ArrayList<MutableList<Pair<Int, Int>>>()
        rowsOfMeasurements.forEachIndexed { rowIndex, currentRow ->
            currentRow.forEachIndexed { measurementIndex, currentMeasurement ->
                val largestBasinPathFromStartingPoint =
                    findLargestBasinPath(rowsOfMeasurements, rowIndex, measurementIndex, currentMeasurement)

                if(largestBasinPathFromStartingPoint.size > 0) {
                    println(
                        "Largest Basin Path (Size ${largestBasinPathFromStartingPoint.size}) starting at [$rowIndex,$measurementIndex]: " + largestBasinPathFromStartingPoint.joinToString(
                            separator = " -> "
                        ) { "[${it.first},${it.second}](${rowsOfMeasurements[it.first][it.second]}) " })
                    printBasin(largestBasinPathFromStartingPoint)
                }

                basinPaths.add(largestBasinPathFromStartingPoint)
            }
        }
        var answer = basinPaths
            .map { basinPath -> basinPath.size }
            .sortedDescending()
            .slice(0..2)
            .reduce { acc, i -> acc * i }
        return answer
    }

    private fun printBasin(largestBasinPathFromStartingPoint: MutableList<Pair<Int, Int>>) {
        largestBasinPathFromStartingPoint.sortBy { coordinate ->
            coordinate.first

        }
    }

    private fun findLargestBasinPath(
        rowsOfMeasurements: List<List<Int>>,
        rowIndex: Int,
        measurementIndex: Int,
        currentMeasurement: Int
    ): MutableList<Pair<Int, Int>> {
        var listOfBasinPaths: List<MutableList<Pair<Int, Int>>> = listOf(mutableListOf())
        if (currentMeasurement == 0) {
            var path = mutableListOf(Pair(rowIndex, measurementIndex))
            listOfBasinPaths = listOf(
                recursiveBasinPath(rowsOfMeasurements, rowIndex, measurementIndex + 1, path), //right
                recursiveBasinPath(rowsOfMeasurements, rowIndex, measurementIndex - 1, path), //left
                recursiveBasinPath(rowsOfMeasurements, rowIndex - 1, measurementIndex, path), //up
                recursiveBasinPath(rowsOfMeasurements, rowIndex + 1, measurementIndex, path) //down
            )
            return listOfBasinPaths.maxByOrNull { possibleMaxPath -> possibleMaxPath.size }!!
        }
        return listOfBasinPaths.first()
    }

    private fun recursiveBasinPath(
        rowsOfMeasurements: List<List<Int>>,
        rowIndex: Int,
        measurementIndex: Int,
        path: MutableList<Pair<Int, Int>>
    ): MutableList<Pair<Int, Int>> {
        if (Pair(rowIndex, measurementIndex) in path) {
            return path
        }
        val currentMeasurement: Int = try {
            rowsOfMeasurements[rowIndex][measurementIndex]
        } catch (e: Exception) {
            return path
        }
        if (currentMeasurement == 9) {
            return path
        }

        path.add(Pair(rowIndex, measurementIndex))
        recursiveBasinPath(rowsOfMeasurements, rowIndex, measurementIndex + 1, path) //right
        recursiveBasinPath(rowsOfMeasurements, rowIndex, measurementIndex - 1, path) //left
        recursiveBasinPath(rowsOfMeasurements, rowIndex - 1, measurementIndex, path) //up
        recursiveBasinPath(rowsOfMeasurements, rowIndex + 1, measurementIndex, path) //down

        return path
    }
}