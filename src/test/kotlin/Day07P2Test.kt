import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day07P2Test {

    @Test
    fun solve() {

    }

    @Test
    fun getFuelCostShouldReturn168WhenMovingAllCrabsToPosition5() {
        val crabPositions = arrayOf(0, 16, 1, 2, 0, 4, 2, 7, 1, 2, 14)
        assertThat(Day07P2().getFuelCost(crabPositions, 5)).isEqualTo(168)
    }
}