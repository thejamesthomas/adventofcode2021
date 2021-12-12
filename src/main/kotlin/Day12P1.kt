import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val input = Path("src/main/resources/Day12Input.txt").readText().split("\n")
    val solutionValue = Day12P1().solve(input)
    println(solutionValue)
}

class Day12P1 {
    fun solve(inputLines: List<String>): Int {
        var caves = ArrayList<Cave>()
        inputLines.forEach { inputLine ->
            val caveName = inputLine.split("-").first()
            val connectionName = inputLine.split("-").last()
            var connection = findExistingCave(caves, connectionName)
            if(connection == null) {
                connection = Cave(
                    connectionName, connectionName[0].isLowerCase()
                )
            }

            var cave = findExistingCave(caves, caveName)
            if (cave == null) {
                cave = Cave(
                    caveName, caveName[0].isLowerCase()
                )
            }

            if(!cave.connections.contains(connection)) cave.connections.add(connection)
            if(!connection.connections.contains(cave)) connection.connections.add(cave)

            if(!caves.contains(cave)) caves.add(cave)
            if(!caves.contains(connection)) caves.add(connection)
        }

        caves.forEach { cave ->
            cave.isConnectedToEnd = cave.connections.any { connection -> connection.name == "end" }
            cave.isConnectedToStart = cave.connections.any { connection -> connection.name == "start" }

            if (cave.name == "end") {
                cave.connections.clear()
            }
        }

        val startCave = caves.find { cave -> cave.name == "start" }!!
        val cavePaths = mutableListOf(mutableListOf(startCave))
        while (!cavePaths.isChartingComplete()) {
            cavePaths.chartNextStep()
        }

        val allValidPaths = cavePaths.filter { cavePath ->
            !cavePath.isInvalid()
        }

        allValidPaths.forEach { validPath ->
            println(validPath.getPathString())
        }
        return allValidPaths.size
    }

    private fun findExistingCave(caves: ArrayList<Cave>, caveName: String) =
        caves.find { cave -> cave.name == caveName }
}

private fun <E> MutableList<E>.chartNextStep() {
    var cavePaths = this as MutableList<MutableList<Cave>>
    var cavePathsToAdd = mutableListOf(mutableListOf<Cave>())
    var cavePathsToRemove = mutableListOf(mutableListOf<Cave>())
    cavePaths
        .filter { cavePath -> !cavePath.isInvalid() }
        .forEach { cavePath ->
            cavePathsToAdd.addAll(cavePath.last().connections
                .filter { connection -> connection.name != "start" }
                .map { connection ->
                    cavePathsToRemove.add(cavePath)
                    val newCavePath = cavePath.toMutableList()
                    newCavePath.add(connection)
                    newCavePath
                })
        }
    cavePathsToRemove.removeIf { cavePath -> cavePath.isEmpty() }
    cavePathsToAdd.removeIf { cavePath -> cavePath.isEmpty() }

    this.removeAll(cavePathsToRemove)
    this.addAll(cavePathsToAdd)
}

private fun <E> MutableList<E>.isChartingComplete(): Boolean {
    val cavePaths = this as MutableList<MutableList<Cave>>
    return cavePaths.all { cavePath -> cavePath.isNotEmpty() && cavePath.isInvalid() || cavePath.last().name == "end" }
}

private fun MutableList<Cave>.getPathString() =
    this.joinToString(",") { cave -> cave.name }

private fun <E> MutableList<E>.isInvalid(): Boolean {
    var invalid = false
    val smallCaveNames = this.filter { unconverted ->
        val cave = unconverted as Cave
        cave.isSmall
    }.map { unconverted ->
        val cave = unconverted as Cave
        cave.name
    }

    smallCaveNames.forEach { caveName ->
        invalid = this.count { unconverted ->
            val cave = unconverted as Cave
            cave.name == caveName
        } > 1
    }

    return invalid
}

data class Cave(
    val name: String,
    val isSmall: Boolean,
    var isConnectedToStart: Boolean = false,
    var isConnectedToEnd: Boolean = false,
    val connections: ArrayList<Cave> = ArrayList()
) {
    override fun toString(): String {
        return this.name
    }
}
