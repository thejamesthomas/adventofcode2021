import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val inputLines = Path("src/main/resources/Day04P2Input.txt").readText().split("\n")
    val solutionValue = Day04P2().solve(inputLines)
    println(solutionValue)
}

private const val BINGO_BOARD_SIDE_SIZE_OFFSET = 4

class Day04P2 {
    fun solve(inputLines: List<String>): Int {
        var bingoInputIndex = 0
        //ingest instructions
        val bingoSequence = BingoSequencer().sequence(inputLines.slice(bingoInputIndex..bingoInputIndex).first())

        bingoInputIndex += 2
        //ingest boards
        var bingoBoards = ArrayList<BingoBoard>()
        while (bingoInputIndex < inputLines.size - BINGO_BOARD_SIDE_SIZE_OFFSET) {
            bingoBoards.add(BingoBoard(inputLines.slice(bingoInputIndex..(bingoInputIndex + BINGO_BOARD_SIDE_SIZE_OFFSET))))
            bingoInputIndex += 6
        }

        var finalBoard: BingoBoard? = null
        bingoSequence.forEach { numberToCall ->
            if(finalBoard != null)
            {
                finalBoard!!.callNextNumber(numberToCall)
                if(finalBoard!!.hasWon())
                    return finalBoard!!.getSumOfUnmarkedNumbers() * numberToCall
            }
            bingoBoards.forEach { board ->
                board.callNextNumber(numberToCall)
            }
            if (bingoBoards.count { board -> board.hasWon() } == bingoBoards.size - 1) {
                finalBoard = bingoBoards.find { board -> !board.hasWon() }!!
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

            return false
        }

        fun getSumOfUnmarkedNumbers(): Int {
            return boardNumbers.filterIndexed { index, _ ->
                !boardState[index]
            }.sum()
        }
    }
}