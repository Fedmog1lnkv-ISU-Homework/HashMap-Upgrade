package SpecialHashMap

import SpecialHashMap.Token.Token
import SpecialHashMap.Token.TokenType
import java.lang.Exception

class Parser(private val text: String) {
    private var pos: Int = 0
    private var currentChar: Char? = text.getOrNull(pos)

    private fun advance() {
        pos++
        currentChar = text.getOrNull(pos)
    }

    private fun skipWhitespace() {
        while (currentChar?.isWhitespace() == true) advance()
    }

    private fun integer(): Int {
        val stringBuilder = StringBuilder()
        while (currentChar?.isDigit() == true) {
            stringBuilder.append(currentChar)
            advance()
        }

        return stringBuilder.toString().toIntOrNull() ?: throw IllegalArgumentException("Invalid integer format")
    }

    fun getNextToken(): Token {
        while (currentChar != null) {
            when {
                currentChar == '>' && checkNext() == '=' -> {
                    advance()
                    advance()
                    return Token(TokenType.Operator.GreaterThanOrEqual, null)
                }

                currentChar == '<' && checkNext() == '>' -> {
                    advance()
                    advance()
                    return Token(TokenType.Operator.NotEqual, null)
                }

                currentChar == '<' && checkNext() == '=' -> {
                    advance()
                    advance()
                    return Token(TokenType.Operator.LessThanOrEqual, null)
                }

                currentChar == '>' -> {
                    advance()
                    return Token(TokenType.Operator.GreaterThan, null)
                }

                currentChar == '<' -> {
                    advance()
                    return Token(TokenType.Operator.LessThan, null)
                }

                currentChar == '=' -> {
                    advance()
                    return Token(TokenType.Operator.Equal, null)
                }

                currentChar?.isDigit() ?: false -> return Token(TokenType.Index, integer())

                currentChar?.isWhitespace() ?: false -> {
                    skipWhitespace()
                    continue
                }

                else -> {
                    throw Exception("Wrong operator")
                }
            }
        }

        return Token(TokenType.EndOfStatement, null)
    }

    private fun checkNext(): Char? = text.getOrNull(pos + 1)
}
