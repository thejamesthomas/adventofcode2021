import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day10P1Test {

    @Test
    fun isCorrupted_corruptedLineShouldBeMarkedAsCorrupted1() {
        val corruptedLine = "{([(<{}[<>[]}>{[]{[(<()>"

        val (actualStatus, actualIncorrectCharacter) = corruptedLine.isCorruptedPair()
        assertThat(actualStatus).isEqualTo(true)
        assertThat(actualIncorrectCharacter).isEqualTo('}')
    }

    @Test
    fun isCorrupted_corruptedLineShouldBeMarkedAsCorrupted2() {
        val corruptedLine = "[[<[([]))<([[{}[[()]]]"

        val (actualStatus, actualIncorrectCharacter) = corruptedLine.isCorruptedPair()
        assertThat(actualStatus).isEqualTo(true)
        assertThat(actualIncorrectCharacter).isEqualTo(')')
    }

    @Test
    fun isCorrupted_corruptedLineShouldBeMarkedAsCorrupted3() {
        val corruptedLine = "[{[{({}]{}}([{[{{{}}([]"

        val (actualStatus, actualIncorrectCharacter) = corruptedLine.isCorruptedPair()
        assertThat(actualStatus).isEqualTo(true)
        assertThat(actualIncorrectCharacter).isEqualTo(']')
    }
    @Test
    fun isCorrupted_corruptedLineShouldBeMarkedAsCorrupted4() {
        val corruptedLine = "[<(<(<(<{}))><([]([]()"

        val (actualStatus, actualIncorrectCharacter) = corruptedLine.isCorruptedPair()
        assertThat(actualStatus).isEqualTo(true)
        assertThat(actualIncorrectCharacter).isEqualTo(')')
    }
    @Test
    fun isCorrupted_corruptedLineShouldBeMarkedAsCorrupted5() {
        val corruptedLine = "<{([([[(<>()){}]>(<<{{"

        val (actualStatus, actualIncorrectCharacter) = corruptedLine.isCorruptedPair()
        assertThat(actualStatus).isEqualTo(true)
        assertThat(actualIncorrectCharacter).isEqualTo('>')
    }

    @Test
    fun isCorrupted_uncorruptedLineShouldNotBeMarkedAsCorrupted1() {
        val corruptedLine = "[({(<(())[]>[[{[]{<()<>>"

        val (actualStatus, actualIncorrectCharacter) = corruptedLine.isCorruptedPair()
        assertThat(actualStatus).isEqualTo(false)
        assertThat(actualIncorrectCharacter).isEqualTo('X')
    }

    @Test
    fun isCorrupted_uncorruptedLineShouldNotBeMarkedAsCorrupted2() {
        val corruptedLine = "[(()[<>])]({[<{<<[]>>("

        val (actualStatus, actualIncorrectCharacter) = corruptedLine.isCorruptedPair()
        assertThat(actualStatus).isEqualTo(false)
        assertThat(actualIncorrectCharacter).isEqualTo('X')
    }
}

