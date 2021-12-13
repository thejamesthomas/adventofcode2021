import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val input = Path("src/main/resources/Day12Input.txt").readText().split("\n")
    val solutionValue = Day12P2().solve(input)
    println(solutionValue)
}

class Day12P2 {
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
                    caveName, (caveName[0].isLowerCase() && caveName != "start")
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
        val cavePaths = mutableListOf(CavePath(mutableListOf(startCave)))
        while (!cavePaths.isChartingComplete()) {
            cavePaths.chartNextStep()
        }

        val allValidPaths = cavePaths.filter { cavePath ->
            !cavePath.isInvalid()
        }

        allValidPaths.forEach { validPath ->
            println(validPath.toString())
        }
        return allValidPaths.size
    }

    private fun findExistingCave(caves: ArrayList<Cave>, caveName: String) =
        caves.find { cave -> cave.name == caveName }

    private fun MutableList<CavePath>.chartNextStep() {
        var newCavePaths = mutableListOf(CavePath())
        this
            .forEach { cavePath ->
                if(cavePath.isInvalid()) {
                    newCavePaths.add(cavePath)
                } else {
                    val tempCavePaths = cavePath.path.last().connections
                        .filter { connection -> connection.name != "start" }
                        .map { connection ->
                            val newCavePath = CavePath(cavePath.path.toMutableList())
                            newCavePath.add(connection)
                            newCavePath
                        }
                    if (tempCavePaths.isEmpty())
                        newCavePaths.add(cavePath)
                    else {
                        newCavePaths.addAll(tempCavePaths)
                    }
                }
            }
        newCavePaths.removeIf { cavePath -> cavePath.path.isEmpty() }

        this.clear()
        this.addAll(newCavePaths)
    }

    private fun MutableList<CavePath>.isChartingComplete(): Boolean {
        return this.all { cavePath -> cavePath.path.isNotEmpty() && cavePath.isInvalid() || cavePath.path.last().name == "end" }
    }

    private fun MutableList<Cave>.getPathString() =
        this.joinToString(",") { cave -> cave.name }

    private fun CavePath.isInvalid(): Boolean {
        val smallCaveNames = this.path.filter { cave ->
            cave.isSmall
        }.map { cave ->
            cave.name
        }.distinct()

        val smallCaveVisitCounts = smallCaveNames.map { caveName ->
            val numberOfVisitsToThisSmallCave = this.path.count { unconverted ->
                val cave = unconverted as Cave
                cave.name == caveName
            }
            numberOfVisitsToThisSmallCave
        }
        val visitedASmallCaveTwiceAtLeastTwice = smallCaveVisitCounts.count { visitCount -> visitCount == 2 } >= 2
        val visitedAnySmallCaveMoreThanTwice = smallCaveVisitCounts.count { visitCount -> visitCount > 2 } > 0

        return visitedASmallCaveTwiceAtLeastTwice || visitedAnySmallCaveMoreThanTwice
    }

    private data class Cave(
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

    private data class CavePath(
        val path: MutableList<Cave> = mutableListOf()
    ) {
        fun add(cave: Cave) {
            path.add(cave)
        }

        override fun toString(): String {
            return path.joinToString(",") { cave -> cave.name }
        }

        override fun equals(other: Any?): Boolean {
            return this.toString() == other.toString()
        }
    }
}
