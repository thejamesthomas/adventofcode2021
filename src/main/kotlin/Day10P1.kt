import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val input = Path("src/main/resources/Day10Input.txt").readText().split("\n")
    val solutionValue = Day10P1().solve(input)
    println(solutionValue)
}

class Day10P1 {
    fun solve(inputLines: List<String>): Int {
        return inputLines.sumOf { line ->
            val (_, corruptedChar) = line.isCorrupted()

            corruptedChar.pointValue()
        }
    }
}

private fun Char.pointValue(): Int {
    return when (this) {
        ')' -> 3
        ']' -> 57
        '}' -> 1197
        '>' -> 25137
        else -> 0
    }
}

private fun String.isCorrupted(): Pair<Boolean, Char> {
    val stack = Stack<Char>()

    this.toCharArray().forEach { currentChar ->
        if (currentChar.isOpeningCharacter()) {
            stack.push(currentChar)
        }
        if (currentChar.isClosingCharacter()) {
            if (stack.size > 0 && stack.peek().isMatchingOpeningCharacterFor(currentChar))
                stack.pop()
            else
                return true to currentChar
        }
    }

    return false to 'X'
}

fun Char.isMatchingOpeningCharacterFor(closingCharacter: Char): Boolean {
    return when (closingCharacter) {
        ')' -> this == '('
        ']' -> this == '['
        '}' -> this == '{'
        '>' -> this == '<'
        else -> false
    }
}

fun Char.isClosingCharacter(): Boolean {
    return this in listOf(')', ']', '}', '>')
}

fun Char.isOpeningCharacter(): Boolean {
    return this in listOf('(', '[', '{', '<')
}
