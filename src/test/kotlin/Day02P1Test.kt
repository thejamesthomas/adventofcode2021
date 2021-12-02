import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day02P1Test {

    @Test
    fun solve_shouldReturnADepthOf10ForTheGivenInput() {
        val input = """
            forward 5
            down 5
            forward 8
            up 3
            down 8
            forward 2
            """.trimIndent().split("\n")

        assertThat(Day02P1().solve(input).first).isEqualTo(10)
    }

    @Test
    fun solve_shouldReturnAHorizontalPositionOf15ForTheGivenInput() {
        val input = """
            forward 5
            down 5
            forward 8
            up 3
            down 8
            forward 2
            """.trimIndent().split("\n")

        assertThat(Day02P1().solve(input).second).isEqualTo(15)
    }
}