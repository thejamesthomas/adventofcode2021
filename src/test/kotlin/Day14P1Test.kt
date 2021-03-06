import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day14P1Test {

    @Test
    fun solveShouldReturn1588WithTheGivenSampleInput() {
        val sampleInput = """
            NNCB

            CH -> B
            HH -> N
            CB -> H
            NH -> C
            HB -> C
            HC -> B
            HN -> C
            NN -> C
            BH -> H
            NC -> B
            NB -> B
            BN -> B
            BB -> N
            BC -> B
            CC -> N
            CN -> C
        """.trimIndent().split("\n")

        assertThat(Day14P1(10).solve(sampleInput)).isEqualTo(1588)
    }
}