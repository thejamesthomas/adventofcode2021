import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day06P2Test {

    @Test
    fun solve_shouldCountNumberOfLanternFishCorrectlyAfter18DaysWithTheSampleInput() {
        val input = "3,4,3,1,2"

        val expectedValue: ULong = 26u
        assertThat(Day06P2(18).solve(input)).isEqualTo(expectedValue)
    }

    @Test
    fun solve_shouldCountNumberOfLanternFishCorrectlyAfter80DaysWithTheSampleInput() {
        val input = "3,4,3,1,2"

        val expectedValue: ULong = 5934u
        assertThat(Day06P2(80).solve(input)).isEqualTo(expectedValue)
    }
}