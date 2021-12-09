import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day09P1Test {

    @Test
    fun solve_shouldReturn15ForTheGivenInput() {
        val inputLines = """
            2199943210
            3987894921
            9856789892
            8767896789
            9899965678
        """.trimIndent().split("\n")

        assertThat(Day09P1().solve(inputLines)).isEqualTo(15)
    }
}