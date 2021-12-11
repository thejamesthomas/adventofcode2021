import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val input = Path("src/main/resources/Day11Input.txt").readText().split("\n")
    val solutionValue = Day11P1().solve(input)
    println(solutionValue)
}

class Day11P1 {
    fun solve(inputLines: List<String>): UInt {
        var octopi = ArrayList<ArrayList<Octopus>>(inputLines.size)

        inputLines.forEachIndexed { index, line ->
            octopi.add(ArrayList())
            line.toCharArray().forEach { octopusInitialValue ->
                octopi[index].add(Octopus(Integer.parseInt(octopusInitialValue.toString())))
            }
        }

        var numberOfFlashes = 0u
        for (step in 1..100) {
//            println("Before Step $step")
//            println(octopi.print())

            octopi.step()
            numberOfFlashes += octopi.flash()
            octopi.resetFlashed()

//            println("After Step $step")
//            println(octopi.print())
        }

        return numberOfFlashes
    }
}

private fun <E> ArrayList<E>.print(): String {
    val output = StringBuilder()

    this.forEach { row ->
        val octopiRow = row as ArrayList<Octopus>
        octopiRow.forEach { octopus ->
            output.append(octopus.print())
        }
        output.append("\n")
    }

    return output.toString()
}

private fun <E> ArrayList<E>.step() {
    this.forEachIndexed { _, row ->
        var octopiRow = row as ArrayList<Octopus>
        octopiRow.forEachIndexed { octopusIndex, _ ->
            octopiRow[octopusIndex].currentValue += 1
        }
    }
}


private fun <E> ArrayList<E>.resetFlashed() {
    this.forEachIndexed { _, row ->
        var octopiRow = row as ArrayList<Octopus>
        octopiRow.forEachIndexed { octopusIndex, _ ->
            if(octopiRow[octopusIndex].hasFlashedThisStep)
                octopiRow[octopusIndex].currentValue = 0

            octopiRow[octopusIndex].hasFlashedThisStep = false
        }
    }
}

private const val OCTOPI_FLASH_VALUE = 9

private fun <E> ArrayList<E>.flash(): UInt {
    var flashCount = 0u
    while (anyOctopusIsReadyToFlash()) {
//        println("Before Flash")
//        println(this.print())
        this.forEachIndexed { rowIndex, row ->
            var octopiRow = row as ArrayList<Octopus>
            octopiRow.forEachIndexed { octopusIndex, octopus ->
                if (octopus.shouldFlash()) {
                    octopiRow[octopusIndex].currentValue = 0
                    this.flashCenteredOn(rowIndex, octopusIndex)
                    octopiRow[octopusIndex].hasFlashedThisStep = true
                    flashCount++
                }
            }
        }
//        println("After Flash")
//        println(this.print())
    }
    return flashCount
}

private fun <E> ArrayList<E>.anyOctopusIsReadyToFlash() = this.any { row ->
    val octopiRow = row as ArrayList<Octopus>
    octopiRow.any { octopus -> octopus.shouldFlash() }
}

private fun <E> ArrayList<E>.flashCenteredOn(rowIndex: Int, octopusIndex: Int) {
    val coordinatesToAttemptToIncrement = listOf(
        Coordinate(rowIndex - 1, octopusIndex - 1), // Top Left
        Coordinate(rowIndex - 1, octopusIndex), // Top Center
        Coordinate(rowIndex - 1, octopusIndex + 1), // Top Right
        Coordinate(rowIndex, octopusIndex + 1), // Right
        Coordinate(rowIndex + 1, octopusIndex + 1), // Bottom Right
        Coordinate(rowIndex + 1, octopusIndex), // Bottom
        Coordinate(rowIndex + 1, octopusIndex - 1), // Bottom Left
        Coordinate(rowIndex, octopusIndex - 1) // Left
    )

    coordinatesToAttemptToIncrement.forEach { coordinate ->
        try {
            var octopiRow = this[coordinate.rowIndex] as ArrayList<Octopus>
            octopiRow[coordinate.columnIndex].currentValue += 1
        } catch (e: Exception) {
            // Do Nothing
        }
    }
}

data class Coordinate(val rowIndex: Int, val columnIndex: Int)
data class Octopus(var currentValue: Int, var hasFlashedThisStep: Boolean = false) {
    fun shouldFlash(): Boolean {
        return currentValue > OCTOPI_FLASH_VALUE && !hasFlashedThisStep
    }

    fun print(): String {
        return if (shouldFlash()) "X"
        else currentValue.toString()
    }
}
