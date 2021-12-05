import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val inputLines = Path("src/main/resources/Day05P1Input.txt").readText().split("\n")
    val solutionValue = Day05P1().solve(inputLines)
    println(solutionValue)
}

private const val MAXIMUM_MAP_SIDE_SIZE = 1000

class Day05P1(private val mapSideSize: Int = MAXIMUM_MAP_SIDE_SIZE) {
    fun solve(inputLines: List<String>): Int {
        val inputLinesWithOnlyHorizontalOrVerticalLines = InputFilterer().filterInput(inputLines)
        var ventMap: ArrayList<Int> = List(mapSideSize * mapSideSize) { 0 } as ArrayList<Int>

        inputLinesWithOnlyHorizontalOrVerticalLines.forEach { line ->
            var coordinateComponents = line.extractFirstAndSecondCoordinateIntoComponents()
            val firstCoordinate = coordinateComponents.first()
            val secondCoordinate = coordinateComponents.last()

            if (line.isHorizontalLine()) {
                val firstXCoordinate = firstCoordinate[X_COORDINATE]
                val secondXCoordinate = secondCoordinate[X_COORDINATE]
                val onlyYCoordinate = firstCoordinate[Y_COORDINATE]
                val largerXCoordinate = if(firstXCoordinate > secondXCoordinate) firstXCoordinate else secondXCoordinate
                val smallerXCoordinate = if(firstXCoordinate < secondXCoordinate) firstXCoordinate else secondXCoordinate

                for(index in (smallerXCoordinate + (onlyYCoordinate * mapSideSize))..(largerXCoordinate + (onlyYCoordinate * mapSideSize)))
                    ventMap[index] = ventMap[index].inc()
            }

            if (line.isVerticalLine()) {
                val firstYCoordinate = firstCoordinate[Y_COORDINATE]
                val secondYCoordinate = secondCoordinate[Y_COORDINATE]
                val onlyXCoordinate = firstCoordinate[X_COORDINATE]
                val largerYCoordinate = if(firstYCoordinate > secondYCoordinate) firstYCoordinate else secondYCoordinate
                val smallerYCoordinate = if(firstYCoordinate < secondYCoordinate) firstYCoordinate else secondYCoordinate

                var index = onlyXCoordinate + (smallerYCoordinate * mapSideSize)
                while(index <= onlyXCoordinate + (largerYCoordinate * mapSideSize)) {
                    ventMap[index] = ventMap[index].inc()

                    index += mapSideSize
                }
            }
        }

        val numberOfPlacesWhereTwoOrMoreLinesOverlap = ventMap.filter { cellValue -> cellValue >= 2 }.count()

        ventMap.forEachIndexed { index, cellValue ->
            if (index % mapSideSize == 0)
                println()
            if(cellValue > 0)
                print(cellValue)
            else
                print(".")
        }
        println()

        return numberOfPlacesWhereTwoOrMoreLinesOverlap
    }

    internal class InputFilterer {
        fun filterInput(inputLines: List<String>): List<String> {
            return inputLines.filter { s: String -> s.isHorizontalLine() || s.isVerticalLine() }
        }
    }
}

private const val X_COORDINATE = 0
private const val Y_COORDINATE = 1

private fun String.isHorizontalLine(): Boolean {
    val firstAndSecondCoordinate = extractFirstAndSecondCoordinateIntoComponents()
    val firstCoordinate = firstAndSecondCoordinate.first()
    val secondCoordinate = firstAndSecondCoordinate.last()

    return firstCoordinate[Y_COORDINATE] == secondCoordinate[Y_COORDINATE]
}

private fun String.isVerticalLine(): Boolean {
    val firstAndSecondCoordinate = extractFirstAndSecondCoordinateIntoComponents()
    val firstCoordinate = firstAndSecondCoordinate.first()
    val secondCoordinate = firstAndSecondCoordinate.last()

    return firstCoordinate[X_COORDINATE] == secondCoordinate[X_COORDINATE]
}

private fun String.extractFirstAndSecondCoordinateIntoComponents() = this
    .split(" -> ")
    .map { coordinatePair ->
        coordinatePair
            .trim()
            .split(",")
            .map { coordinateComponent -> Integer.parseInt(coordinateComponent) }
    }
