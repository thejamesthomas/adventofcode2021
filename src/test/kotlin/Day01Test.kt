import org.junit.jupiter.api.Test

import org.assertj.core.api.Assertions.*

internal class Day01Test {

    @Test
    fun solveShouldReturnACountOf3When3LinesAreLargerThanThePreviousLines() {
        assertThat(Day01().solve(listOf(
            "1",
            "2",
            "3",
            "4"))).isEqualTo(3)
    }

    @Test
    fun solve_shouldReturnACountOf3When3LinesAreLargerThanThePreviousLinesWithSomeLinesThatAreSmaller() {
        assertThat(Day01().solve(listOf(
            "1",
            "1",
            "2",
            "2",
            "3",
            "3",
            "4",
            "4"
        ))).isEqualTo(3)
    }

    @Test
    fun solve_shouldReturn7WithTheExampleInput() {
        assertThat(Day01().solve(listOf(
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
        ))).isEqualTo(7)
    }
}