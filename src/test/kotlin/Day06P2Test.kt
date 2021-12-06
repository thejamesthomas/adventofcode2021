import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day06P2Test {

    @Test
    fun solve_shouldCountNumberOfLanternFishCorrectlyAfter18DaysWithTheSampleInput() {
        val input = "3,4,3,1,2"

        assertThat(Day06P2(18).solve(input)).isEqualTo(26)
    }

    @Test
    fun solve_shouldCountNumberOfLanternFishCorrectlyAfter80DaysWithTheSampleInput() {
        val input = "3,4,3,1,2"

        assertThat(Day06P2(80).solve(input)).isEqualTo(5934)
    }
}