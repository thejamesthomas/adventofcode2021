import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day07P1Test {

    @Test
    fun solve() {

    }

    @Test
    fun getFuelCostShouldReturn41WhenMovingAllCrabsToPosition1() {
        val crabPositions = arrayOf(0,16,1,2,0,4,2,7,1,2,14)
        assertThat(Day07P1().getFuelCost(crabPositions, 1)).isEqualTo(41)
    }

    @Test
    fun getFuelCostShouldReturn39WhenMovingAllCrabsToPosition3() {
        val crabPositions = arrayOf(0,16,1,2,0,4,2,7,1,2,14)
        assertThat(Day07P1().getFuelCost(crabPositions, 3)).isEqualTo(39)
    }

    @Test
    fun getFuelCostShouldReturn71WhenMovingAllCrabsToPosition10() {
        val crabPositions = arrayOf(0,16,1,2,0,4,2,7,1,2,14)
        assertThat(Day07P1().getFuelCost(crabPositions, 10)).isEqualTo(71)
    }
}