import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val inputLines = Path("src/main/resources/Day04P1Input.txt").readText().split("\n")
    val solutionValue = Day04P1().solve(inputLines)
    println(solutionValue)
}

private const val BINGO_BOARD_SIDE_SIZE_OFFSET = 4

class Day04P1 {
    fun solve(inputLines: List<String>): Int {
        var bingoInputIndex = 0
        //ingest instructions]
        val bingoSequence = BingoSequencer().sequence(inputLines.slice(bingoInputIndex..bingoInputIndex).first())

        bingoInputIndex += 2
        //ingest boards
        var bingoBoards = ArrayList<BingoBoard>()
        while (bingoInputIndex < 597) {
            bingoBoards.add(BingoBoard(inputLines.slice(bingoInputIndex..(bingoInputIndex + BINGO_BOARD_SIDE_SIZE_OFFSET))))
            bingoInputIndex += 6
        }

        bingoSequence.forEach { numberToCall ->
            bingoBoards.forEach { board ->
                board.callNextNumber(numberToCall)
            }
            if (bingoBoards.any { board -> board.hasWon() }) {
                return bingoBoards.find { board -> board.hasWon() }!!.getSumOfUnmarkedNumbers() * numberToCall
            }
        }

        return 0
    }

    internal class BingoSequencer {
        fun sequence(bingoSequence: String): List<Int> {
            return bingoSequence.split(",").map { s -> Integer.parseInt(s) }
        }
    }

    internal class BingoBoard(boardNumberLines: List<String>) {
        private val boardState: ArrayList<Boolean>
        private val boardNumbers: ArrayList<Int>

        init {
            boardNumbers = boardNumberLines
                .map { line ->
                    line.trim()
                        .split(" ")
                        .filter { s -> s.isNotBlank() }
                        .map { number -> Integer.parseInt(number) }
                }
                .flatten() as ArrayList<Int>

            boardState = boardNumbers.map { false } as ArrayList<Boolean>
        }

        fun callNextNumber(number: Int): Boolean {
            val indexOfNumber = boardNumbers.indexOf(number)
            if (indexOfNumber < 0) return false
            boardState[indexOfNumber] = true
            return true
        }

        fun hasWon(): Boolean {
            //check for horizontal solves
            var rowIndex = 0
            while (rowIndex < 5) {
                if (boardState.slice((rowIndex * 5)..((rowIndex * 5) + 4)).all { b -> b }) return true
                rowIndex += 1
            }

            //check for vertical solves
            var columnModulo = 5
            var columnIndex = 0
            while (columnIndex < 5) {
                if (boardState.filterIndexed { index, b -> index % columnModulo == columnIndex }
                        .all { b -> b }) return true
                columnIndex += 1
            }

            //check for diagonal solves
            // indexes 0, 6, 12, 18, 24
            // indexes 4, 8, 12, 16, 20
            val upperLeftLowerRightIndices = listOf(0, 6, 12, 18, 24)
            val lowerLeftUpperRightIndices = listOf(4, 8, 12, 16, 20)
            if (boardState.filterIndexed { index, b -> upperLeftLowerRightIndices.contains(index) }
                    .all { b -> b }) return true
            if (boardState.filterIndexed { index, b -> lowerLeftUpperRightIndices.contains(index) }
                    .all { b -> b }) return true
            return false
        }

        fun getSumOfUnmarkedNumbers(): Int {
            return boardNumbers.filterIndexed { index, _ ->
                !boardState[index]
            }.sum()
        }
    }
}