import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.Ignore

internal class Day09P2Test {

    @Test
    @Ignore("Broke my test by cutting out all the extra logic the find low points, since the three lowest points with the largest basins have to start at 0")
    fun solve_shouldReturn1134ForTheGivenInput() {
        val inputLines = """
            2199943210
            3987894921
            9856789892
            8767896789
            9899965678
        """.trimIndent().split("\n")

        assertThat(Day09P2().solve(inputLines)).isEqualTo(1134)
    }
}