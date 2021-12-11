import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day10P2Test {

    @Test
    fun solve_firstSampleInputShouldScore288957Points() {
        val expectedValue: ULong = 288957u
        assertThat(Day10P2().solve(listOf("[({(<(())[]>[[{[]{<()<>>"))).isEqualTo(expectedValue)
    }

    @Test
    fun solve_secondSampleInputShouldScore5566Points() {
        val expectedValue: ULong = 5566u
        assertThat(Day10P2().solve(listOf("[(()[<>])]({[<{<<[]>>("))).isEqualTo(expectedValue)
    }
}