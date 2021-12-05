import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val inputLines = Path("src/main/resources/Day05P2Input.txt").readText().split("\n")
    val solutionValue = Day05P2().solve(inputLines)
    println(solutionValue)
}

private const val MAXIMUM_MAP_SIDE_SIZE = 1000

class Day05P2(private val mapSideSize: Int = MAXIMUM_MAP_SIDE_SIZE) {
    fun solve(inputLines: List<String>): Int {
        var ventMap: ArrayList<Int> = List(mapSideSize * mapSideSize) { 0 } as ArrayList<Int>

        inputLines.forEach { line ->
            var coordinateComponents = line.extractFirstAndSecondCoordinateIntoComponents()
            val startingCoordinate = coordinateComponents.first()
            val endingCoordinate = coordinateComponents.last()

            if (line.isHorizontalLine()) {
                val firstXCoordinate = startingCoordinate[X_COORDINATE]
                val secondXCoordinate = endingCoordinate[X_COORDINATE]
                val onlyYCoordinate = startingCoordinate[Y_COORDINATE]

                val largerXCoordinate = if(firstXCoordinate > secondXCoordinate) firstXCoordinate else secondXCoordinate
                val smallerXCoordinate = if(firstXCoordinate < secondXCoordinate) firstXCoordinate else secondXCoordinate

                for(index in (smallerXCoordinate + (onlyYCoordinate * mapSideSize))..(largerXCoordinate + (onlyYCoordinate * mapSideSize)))
                    ventMap[index] = ventMap[index].inc()
            }
            else if (line.isVerticalLine()) {
                val firstYCoordinate = startingCoordinate[Y_COORDINATE]
                val secondYCoordinate = endingCoordinate[Y_COORDINATE]
                val onlyXCoordinate = startingCoordinate[X_COORDINATE]
                val largerYCoordinate = if(firstYCoordinate > secondYCoordinate) firstYCoordinate else secondYCoordinate
                val smallerYCoordinate = if(firstYCoordinate < secondYCoordinate) firstYCoordinate else secondYCoordinate

                var index = onlyXCoordinate + (smallerYCoordinate * mapSideSize)
                while(index <= onlyXCoordinate + (largerYCoordinate * mapSideSize)) {
                    ventMap[index] = ventMap[index].inc()

                    index += mapSideSize
                }
            }
            else {
                var xIndex = startingCoordinate[X_COORDINATE]
                var yIndex = startingCoordinate[Y_COORDINATE]

                while(xIndex != endingCoordinate[X_COORDINATE] && yIndex != endingCoordinate[Y_COORDINATE]) {
                    val cellIndex = xIndex + (yIndex * mapSideSize)
                    ventMap[cellIndex] = ventMap[cellIndex].inc()

                    xIndex = if(xIndex < endingCoordinate[X_COORDINATE]) xIndex.inc() else xIndex.dec()
                    yIndex = if(yIndex > endingCoordinate[Y_COORDINATE]) yIndex.dec() else yIndex.inc()
                }
                val cellIndex = xIndex + (yIndex * mapSideSize)
                ventMap[cellIndex] = ventMap[cellIndex].inc()
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
