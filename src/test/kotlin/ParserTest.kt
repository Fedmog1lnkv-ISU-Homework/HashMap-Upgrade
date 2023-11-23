import SpecialHashMap.Parser
import SpecialHashMap.Token.TokenType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ParserTest {

    @Test
    fun testGreaterThanToken() {
        val parser = Parser(">")
        val token = parser.getNextToken()
        assertEquals(TokenType.Operator.GreaterThan, token.type)
        assertNull(token.value)
    }

    @Test
    fun testGreaterThanOrEqualToken() {
        val parser = Parser(">=")
        val token = parser.getNextToken()
        assertEquals(TokenType.Operator.GreaterThanOrEqual, token.type)
        assertNull(token.value)
    }

    @Test
    fun testLessThanToken() {
        val parser = Parser("<")
        val token = parser.getNextToken()
        assertEquals(TokenType.Operator.LessThan, token.type)
        assertNull(token.value)
    }

    @Test
    fun testLessThanOrEqualToken() {
        val parser = Parser("<=")
        val token = parser.getNextToken()
        assertEquals(TokenType.Operator.LessThanOrEqual, token.type)
        assertNull(token.value)
    }

    @Test
    fun testNotEqualToken() {
        val parser = Parser("<>")
        val token = parser.getNextToken()
        assertEquals(TokenType.Operator.NotEqual, token.type)
        assertNull(token.value)
    }

    @Test
    fun testEqualToken() {
        val parser = Parser("=")
        val token = parser.getNextToken()
        assertEquals(TokenType.Operator.Equal, token.type)
        assertNull(token.value)
    }

    @Test
    fun testIndexToken() {
        val parser = Parser("42")
        val token = parser.getNextToken()
        assertEquals(TokenType.Index, token.type)
        assertEquals(42, token.value)
    }

    @Test
    fun testEndOfStatementToken() {
        val parser = Parser("   ")
        val token = parser.getNextToken()
        assertEquals(TokenType.EndOfStatement, token.type)
        assertNull(token.value)
    }
}
