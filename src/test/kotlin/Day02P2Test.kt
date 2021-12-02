import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day02P2Test {

    @Test
    fun solve_shouldNotChangeTheDepthIfTheAimDoesNotChange() {
        val input = """
            forward 10
            up 5
            down 5
            forward 10
        """.trimIndent().split("\n")

        assertThat(Day02P2().solve(input).depth).isEqualTo(0)
    }

    @Test
    fun solve_shouldChangeTheDepthWhenPointedDownAndMovedForward() {
        val input = """
            down 5
            forward 10
        """.trimIndent().split("\n")

        val actualPosition = Day02P2().solve(input)
        assertThat(actualPosition.depth).isEqualTo(50)
        assertThat(actualPosition.horizontal).isEqualTo(10)
    }
}