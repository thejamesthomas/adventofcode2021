import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day13Test {

    @Test
    fun solve_shouldReturn17ForTheGivenSampleInput() {
        val sampleInput = """
            6,10
            0,14
            9,10
            0,3
            10,4
            4,11
            6,0
            6,12
            4,1
            0,13
            10,12
            3,4
            3,0
            8,4
            1,10
            2,14
            8,10
            9,0

            fold along y=7
        """.trimIndent().split("\n")

        assertThat(Day13P1().solve(sampleInput)).isEqualTo(17)
    }

    @Test
    fun solve_shouldReturn16ForTheGivenSampleInputWhenPerformingTwoFolds() {
        val sampleInput = """
            6,10
            0,14
            9,10
            0,3
            10,4
            4,11
            6,0
            6,12
            4,1
            0,13
            10,12
            3,4
            3,0
            8,4
            1,10
            2,14
            8,10
            9,0

            fold along y=7
            fold along x=5
        """.trimIndent().split("\n")

        assertThat(Day13P1().solve(sampleInput)).isEqualTo(16)
    }
}