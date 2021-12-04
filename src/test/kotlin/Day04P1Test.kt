import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day04P1Test {

    @Test
    fun solve() {
    }

    private val boardInput1: List<String>
        get() {
            var input = """
            22 13 17 11  0
             8  2 23  4 24
            21  9 14 16  7
             6 10  3 18  5
             1 12 20 15 19
            """.trimIndent().split("\n")
            return input
        }

    @Test
    fun BingoBoard_shouldReturnTrueWhenCallNextNumberFindsThatNumberInTheBoard() {

        val bingoBoard = Day04P1.BingoBoard(boardInput1)

        assertThat(bingoBoard.callNextNumber(22)).isEqualTo(true)
    }

    @Test
    fun BingoBoard_shouldReturnFalseWhenCallNextNumberDoesNotFindThatNumberInTheBoard() {

        val bingoBoard = Day04P1.BingoBoard(boardInput1)

        assertThat(bingoBoard.callNextNumber(50)).isEqualTo(false)
    }

    @Test
    fun BingoBoard_shouldReturnTrueForHasWonWhen5NumbersInARowHorizontallyAreFound() {
        val bingoBoard = Day04P1.BingoBoard(boardInput1)
        assertThat(bingoBoard.hasWon()).isFalse

        bingoBoard.callNextNumber(22)
        bingoBoard.callNextNumber(13)
        bingoBoard.callNextNumber(17)
        bingoBoard.callNextNumber(11)
        bingoBoard.callNextNumber(0)

        assertThat(bingoBoard.hasWon()).isTrue
    }

    @Test
    fun BingoBoard_shouldReturnTrueForHasWonWhen5NumbersInARowVerticallyAreFound() {
        val bingoBoard = Day04P1.BingoBoard(boardInput1)
        assertThat(bingoBoard.hasWon()).isFalse

        bingoBoard.callNextNumber(13)
        bingoBoard.callNextNumber(2)
        bingoBoard.callNextNumber(9)
        bingoBoard.callNextNumber(10)
        bingoBoard.callNextNumber(12)

        assertThat(bingoBoard.hasWon()).isTrue
    }

    @Test
    fun BingoBoard_shouldReturnTrueForHasWonWhen5NumbersInARowDiagonallyAreFound() {
        val bingoBoard = Day04P1.BingoBoard(boardInput1)
        assertThat(bingoBoard.hasWon()).isFalse

        bingoBoard.callNextNumber(22)
        bingoBoard.callNextNumber(2)
        bingoBoard.callNextNumber(14)
        bingoBoard.callNextNumber(18)
        bingoBoard.callNextNumber(19)

        assertThat(bingoBoard.hasWon()).isTrue
    }
}