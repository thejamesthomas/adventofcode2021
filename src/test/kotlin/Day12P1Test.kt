import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day12P1Test {

    @Test
    fun solve_shouldReturn10WithSimpleSampleInput() {
        val simpleSampleInput = """
            start-A
            start-b
            A-c
            A-b
            b-d
            A-end
            b-end
        """.trimIndent().split("\n")
        assertThat(Day12P1().solve(simpleSampleInput)).isEqualTo(10)
    }


    @Test
    fun solve_shouldReturn19WithSecondSimpleSampleInput() {
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
        assertThat(Day12P1().solve(simpleSampleInput)).isEqualTo(19)
    }

    @Test
    fun solve_shouldReturn226WithThirdSimpleSampleInput() {
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
        assertThat(Day12P1().solve(simpleSampleInput)).isEqualTo(226)
    }
}