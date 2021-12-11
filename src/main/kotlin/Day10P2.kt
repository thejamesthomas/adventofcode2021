import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val input = Path("src/main/resources/Day10Input.txt").readText().split("\n")
    val solutionValue = Day10P2().solve(input)
    println(solutionValue)
}

class Day10P2 {
    fun solve(inputLines: List<String>): ULong {
        val pointValues = inputLines
            .filter { line -> !line.isCorruptedBoolean() }
            .map { line ->
                val solvedString = line.findSolution()
                println(solvedString)

                solvedString.pointValue()
            }
            .sorted()
        return pointValues[pointValues.size / 2]
    }
}

private fun String.pointValue(): ULong {
    return this.toCharArray().map { char ->
        char.pointValue()
    }.reduce { acc, pointValue -> acc * 5u + pointValue }
}

private fun Char.pointValue(): ULong {
    return when (this) {
        ')' -> 1u
        ']' -> 2u
        '}' -> 3u
        '>' -> 4u
        else -> 0u
    }
}

private fun String.findSolution(): String {
    val stack = Stack<Char>()

    // Assume that the input isn't invalid, just incomplete. So push and pop at will
    this.toCharArray().forEach { currentChar ->
        if (currentChar.isOpeningCharacter()) {
            stack.push(currentChar)
        } else {
            stack.pop()
        }
    }

    // build solution
    var solutionBuilder = StringBuilder()
    while (stack.size > 0) {
        solutionBuilder.append(stack.pop().getMatchingClosingCharacter())
    }

    return solutionBuilder.toString()
}

private fun Char.getMatchingClosingCharacter(): Char {
    return when (this) {
        '(' -> ')'
        '[' -> ']'
        '{' -> '}'
        '<' -> '>'
        else -> 'X'
    }
}

private fun String.isCorruptedBoolean(): Boolean {
    val stack = Stack<Char>()

    this.toCharArray().forEach { currentChar ->
        if (currentChar.isOpeningCharacter()) {
            stack.push(currentChar)
        }
        if (currentChar.isClosingCharacter()) {
            if (stack.size > 0 && stack.peek().isMatchingOpeningCharacterFor(currentChar))
                stack.pop()
            else
                return true
        }
    }
    return false
}
