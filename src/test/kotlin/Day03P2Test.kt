import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day03P2Test {

    @Test
    fun solve_shouldReturnCorrectValuesWithGivenInput() {
        val input = """
        00100
        11110
        10110
        10111
        10101
        01111
        00111
        11100
        10000
        11001
        00010
        01010
        """.trimIndent().split("\n")

        val actualSolution = Day03P2().solve(input)
        assertThat(actualSolution.oxygenGenerator).isEqualTo(23)
        assertThat(actualSolution.co2Scrubber).isEqualTo(10)
    }

    @Test
    fun getMostCommonDigitForIndex_shouldReturnTheCorrectMostCommonDigit() {
        val input = """
        00100
        11110
        10110
        10111
        10101
        01111
        00111
        11100
        10000
        11001
        00010
        01010
        """.trimIndent().split("\n")

        assertThat(input.getMostCommonDigitForIndex(0)).isEqualTo(1)
        assertThat(input.getMostCommonDigitForIndex(4)).isEqualTo(0)
    }
    @Test
    fun getMostCommonDigitForIndex_shouldReturnTheCorrectMostCommonDigitOf1WhenTwoOutOfThreeAre1() {
        val input = """
        11110
        11100
        11001
        """.trimIndent().split("\n")

        assertThat(input.getMostCommonDigitForIndex(2)).isEqualTo(1)
    }

    @Test
    fun getMostCommonDigitForIndex_shouldReturnNegative1WhenItIsATie() {
        val input = """
        10110
        10111
        """.trimIndent().split("\n")

        assertThat(input.getMostCommonDigitForIndex(4)).isNotEqualTo(1)
        assertThat(input.getMostCommonDigitForIndex(4)).isNotEqualTo(0)
        assertThat(input.getMostCommonDigitForIndex(4)).isEqualTo(-1)
    }
}