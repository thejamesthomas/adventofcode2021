import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val input = Path("src/main/resources/Day06P1Input.txt").readText()
    val solutionValue = Day06P1(80).solve(input)
    println(solutionValue)
}

private const val LANTERNFISH_INITIAL_SPAWN_TIMER = 8
private const val LANTERNFISH_SUBSEQUENT_SPAWN_TIMER = 6

private const val DEFAULT_NUMBER_OF_DAYS_TO_SIMULATE = 80

class Day06P1(private val daysToSimulate: Int = DEFAULT_NUMBER_OF_DAYS_TO_SIMULATE) {
    fun solve(input: String): Int {
        var dayCount = 1
        println("Initial state: $input")

        var lanternFishSwarm = input.split(",")
            .map { s -> Integer.parseInt(s) }
            .map { timeToSpawn -> LanternFish(timeToSpawn) }
            .toMutableList()
        while (dayCount <= daysToSimulate) {

            var lanternFishToAdd = ArrayList<LanternFish>()
            lanternFishSwarm.forEach { fish ->
                if(fish.incrementDay() == -1) {
                    lanternFishToAdd.add(fish.spawn())
                }
            }
            lanternFishSwarm.addAll(lanternFishToAdd)

//            println(String.format("After %2d day${if (dayCount == 1) ":" else "s: "} %s", dayCount, lanternFishSwarm.joinToString(",")))

            dayCount++
        }

        return lanternFishSwarm.size
    }

    class LanternFish(var timeTilSpawn: Int = LANTERNFISH_INITIAL_SPAWN_TIMER) {
        fun incrementDay(): Int {
            timeTilSpawn = timeTilSpawn.dec()
            return timeTilSpawn
        }

        fun spawn(): LanternFish {
            timeTilSpawn = LANTERNFISH_SUBSEQUENT_SPAWN_TIMER
            return LanternFish(LANTERNFISH_INITIAL_SPAWN_TIMER)
        }

        override fun toString(): String {
            return timeTilSpawn.toString()
        }
    }
}