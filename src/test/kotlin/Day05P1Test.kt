import org.assertj.core.api.Assertions.`in`
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day05P1Test {

    @Test
    fun solve_shouldCountTheHorizontalLinesThatCross() {
        val input = """
        0,9 -> 5,9
        8,0 -> 0,8
        9,4 -> 3,4
        2,2 -> 2,1
        7,0 -> 7,4
        6,4 -> 2,0
        0,9 -> 2,9
        3,4 -> 1,4
        0,0 -> 8,8
        5,5 -> 8,2
        """.trimIndent().split("\n")

        assertThat(Day05P1(10).solve(input)).isEqualTo(5)
    }

    @Test
    fun solve_shouldReturn1WhenTwoLinesOverlapAtOncLocation() {
        val input = """
        9,4 -> 3,4
        3,4 -> 1,4
        """.trimIndent().split("\n")

        assertThat(Day05P1(10).solve(input)).isEqualTo(1)
    }

    @Test
    fun solve_shouldReturn2WhenThreeLinesOverlapAtTwoLocations() {
        val input = """
        7,0 -> 7,4
        9,4 -> 3,4
        3,4 -> 1,4
        """.trimIndent().split("\n")

        assertThat(Day05P1(10).solve(input)).isEqualTo(2)
    }

    @Test
    fun InputFilterer_shouldKeepHorizontalLines() {
        val input = """
        0,9 -> 5,9
        9,4 -> 3,4
        0,0 -> 1,1
        """.trimIndent().split("\n")

        assertThat(Day05P1.InputFilterer().filterInput(input)).hasSize(2)
    }

    @Test
    fun InputFilterer_shouldKeepVerticalLines() {
        val input = """
        9,0 -> 9,5
        4,9 -> 4,3
        0,0 -> 1,1
        """.trimIndent().split("\n")

        assertThat(Day05P1.InputFilterer().filterInput(input)).hasSize(2)
    }
}