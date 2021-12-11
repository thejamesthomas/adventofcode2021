import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day11P1Test {

    @Test
    fun solve_shouldReturn1656ForGivenInput() {
        val inputLines = """
            5483143223
            2745854711
            5264556173
            6141336146
            6357385478
            4167524645
            2176841721
            6882881134
            4846848554
            5283751526
        """.trimIndent().split("\n")

        assertThat(Day11P1().solve(inputLines)).isEqualTo(1656u)
    }
}