import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val input = Path("src/main/resources/Day06P2Input.txt").readText()
    val solutionValue = Day06P2().solve(input)
    println(solutionValue)
}

private const val LANTERNFISH_INITIAL_SPAWN_TIMER = 8
private const val LANTERNFISH_SUBSEQUENT_SPAWN_TIMER = 6

private const val DEFAULT_NUMBER_OF_DAYS_TO_SIMULATE = 256

private const val LANTERNFISH_BIN_SIZE = 9

class Day06P2(private val daysToSimulate: Int = DEFAULT_NUMBER_OF_DAYS_TO_SIMULATE) {
    fun solve(input: String): ULong {
        var lanternFishSwarmPen = Array<ULong>(LANTERNFISH_BIN_SIZE) { _ -> 0u }
        println("Heap Size: ${Runtime.getRuntime().maxMemory()}")
        var dayCount = 1
        println("Initial state: $input")
        input.split(",")
            .map { s -> Integer.parseInt(s) }
            .forEach { initialDayToSpawn ->
                lanternFishSwarmPen[initialDayToSpawn] = lanternFishSwarmPen[initialDayToSpawn].inc()
            }

        while (dayCount <= daysToSimulate) {
            val lanternFishToSpawnToday = lanternFishSwarmPen[0]
            lanternFishSwarmPen.copyInto(lanternFishSwarmPen, 0, 1, 9)

            lanternFishSwarmPen[LANTERNFISH_SUBSEQUENT_SPAWN_TIMER] = lanternFishSwarmPen[LANTERNFISH_SUBSEQUENT_SPAWN_TIMER] + lanternFishToSpawnToday
            lanternFishSwarmPen[LANTERNFISH_INITIAL_SPAWN_TIMER] = lanternFishToSpawnToday

            println(String.format("After %2d day${if (dayCount == 1) ":" else "s: "} %s", dayCount, lanternFishSwarmPen.sum()))

            dayCount++
        }

        return lanternFishSwarmPen.sum()
    }
}