import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day12P2Test {

    @Test
    fun solve_shouldReturn36WithSimpleSampleInput() {
        val simpleSampleInput = """
            start-A
            start-b
            A-c
            A-b
            b-d
            A-end
            b-end
        """.trimIndent().split("\n")
        assertThat(Day12P2().solve(simpleSampleInput)).isEqualTo(36)
    }


    @Test
    fun solve_shouldReturn103WithSecondSimpleSampleInput() {
        val simpleSampleInput = """
            dc-end
            HN-start
            start-kj
            dc-start
            dc-HN
            LN-dc
            HN-end
            kj-sa
            kj-HN
            kj-dc
        """.trimIndent().split("\n")
        assertThat(Day12P2().solve(simpleSampleInput)).isEqualTo(103)
    }

    @Test
    fun solve_shouldReturn3509WithThirdSimpleSampleInput() {
        val simpleSampleInput = """
            fs-end
            he-DX
            fs-he
            start-DX
            pj-DX
            end-zg
            zg-sl
            zg-pj
            pj-he
            RW-he
            fs-DX
            pj-RW
            zg-RW
            start-pj
            he-WI
            zg-he
            pj-fs
            start-RW
        """.trimIndent().split("\n")
        assertThat(Day12P2().solve(simpleSampleInput)).isEqualTo(3509)
    }
}