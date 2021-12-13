import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val input = Path("src/main/resources/Day13Input.txt").readText().split("\n")
    val solutionValue = Day13P1().solve(input)
    println(solutionValue)
}

private const val X_COORDINATE = 0
private const val Y_COORDINATE = 1

class Day13P1 {
    fun solve(inputLines: List<String>): Int {

        val paperSheet = buildSheet(inputLines)

        printSheet(paperSheet)


        val indexOfBoundaryBetweenCoordinatesAndFoldCommands = inputLines.indexOf("")
        val indexOfStartOfFoldCommands = indexOfBoundaryBetweenCoordinatesAndFoldCommands + 1
        val foldCommands = inputLines.slice((indexOfStartOfFoldCommands) until inputLines.size)
        foldCommands.forEach { foldCommand ->
            foldSheet(paperSheet, foldCommand)
        }

        println()
        printSheet(paperSheet)

        return paperSheet.sumOf { row -> row.count { rowValue -> rowValue == '#' } }
    }

    private fun printSheet(paperSheet: ArrayList<ArrayList<Char>>) {
        paperSheet.forEach { row ->
            row.forEach { columnValue ->
                print(columnValue)
            }
            println()
        }
    }

    private fun foldSheet(paperSheet: ArrayList<ArrayList<Char>>, foldCommand: String) {
        val axis = foldCommand.split("=")[0].last().toString().toLowerCase()
        val axisValue = Integer.parseInt(foldCommand.split("=")[1])

        if (axis == "y") {
            val foldedRows = paperSheet.slice(axisValue + 1 until paperSheet.size).reversed()
            for (i in foldedRows.indices) {
                paperSheet[i].merge(foldedRows[i])
            }
            for (i in (axisValue) until paperSheet.size)
                paperSheet.removeAt(axisValue)
        }
        if (axis == "x") {
            val foldedColumnIndices = (axisValue + 1) until paperSheet[0].size
            for (rowIndex in 0 until paperSheet.size) {
                paperSheet[rowIndex][axisValue] = '|'
            }
            for (rowIndex in 0 until paperSheet.size) {
                val foldedColumn = paperSheet[rowIndex].slice(axisValue until paperSheet[rowIndex].size).reversed()
                for (colIndex in 0 until axisValue) {
                    if (foldedColumn[colIndex] == '#')
                        paperSheet[rowIndex][colIndex] = '#'
                }
            }
            for (rowIndex in 0 until paperSheet.size) {
                while(paperSheet[rowIndex].size > axisValue) {
                    paperSheet[rowIndex].removeAt(axisValue)
                }
            }
            println()
//            for(i in paperSheet.indices) {
//                for (j in axisValue until paperSheet[0].size - 1) {
//                    val foldedColumns = paperSheet[i].slice(foldedColumnIndexes).reversed()
//                    for (k in foldedColumns.indices) {
//                        if (foldedColumns[k] == '#')
//                            paperSheet[i][j] = '#'
//                    }
//                }
//            }

//            for (i in paperSheet[0].indices) {
//                for (j in axisValue until paperSheet[0].size) {
//                    paperSheet[i].removeAt(axisValue)
//                }
//            }
        }
    }

    private fun buildSheet(inputLines: List<String>): ArrayList<ArrayList<Char>> {
        val indexOfBoundaryBetweenCoordinatesAndFoldCommands = inputLines.indexOf("")
        val indexOfEndOfCoordinates = indexOfBoundaryBetweenCoordinatesAndFoldCommands - 1
        val maxX = inputLines
            .slice(0..indexOfEndOfCoordinates)
            .maxOf { line -> getXCoordinate(line) }
        val maxY = inputLines
            .slice(0..indexOfEndOfCoordinates)
            .maxOf { line -> getYCoordinate(line) }

        var sheet = ArrayList<ArrayList<Char>>()
        for (i in 0..maxY) {
            val row = ArrayList<Char>(maxX)
            for (j in 0..maxX) {
                row.add('.')
            }
            sheet.add(row)
        }

        inputLines
            .slice(0..indexOfEndOfCoordinates)
            .forEach { line ->
                val x = getXCoordinate(line)
                val y = getYCoordinate(line)

                sheet[y][x] = '#'
            }

        return sheet
    }

    private fun getYCoordinate(line: String) = Integer.parseInt(line.split(",")[Y_COORDINATE])

    private fun getXCoordinate(line: String) = Integer.parseInt(line.split(",")[X_COORDINATE])
}

private fun ArrayList<Char>.merge(rowToMerge: ArrayList<Char>) {
    for (i in this.indices) {
        if (rowToMerge[i] == '#')
            this[i] = '#'
    }
}
