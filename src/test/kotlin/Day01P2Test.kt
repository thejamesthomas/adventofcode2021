import org.junit.jupiter.api.Test

import org.assertj.core.api.Assertions.*

internal class Day01P2Test {

    @Test
    fun solveShouldReturnACountOf3WhenThereIsOnlyOneWindowOfMeasurementsGreaterThanItsPredecessor() {
        assertThat(Day01P2().solve(listOf(
            "1",
            "2",
            "3",
            "4"))).isEqualTo(1)
    }

    @Test
    fun solve_shouldReturn5WithTheGivenTestInput() {
        assertThat(Day01P2().solve(listOf(
            "199",
            "200",
            "208",
            "210",
            "200",
            "207",
            "240",
            "269",
            "260",
            "263"
        ))).isEqualTo(5)
    }
}