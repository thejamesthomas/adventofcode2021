import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day03P1Test {

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

        val actualSolution = Day03P1().solve(input)
        assertThat(actualSolution.gamma).isEqualTo(22)
        assertThat(actualSolution.epsilon).isEqualTo(9)
    }
}