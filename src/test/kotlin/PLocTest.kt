package SpecialHashMap

import SpecialHashMap.Token.TokenType
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.Assertions.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class PlocTest {

    private val map = SpecialHashMap()

    @BeforeAll
    fun beforeAll() {
        map["value1"] = 1
        map["value2"] = 2
        map["value3"] = 3
        map["1"] = 10
        map["2"] = 20
        map["3"] = 30
        map["(1, 5)"] = 100
        map["(5, 5)"] = 200
        map["(10, 5)"] = 300
        map["(1, 5, 3)"] = 400
        map["(5, 5, 4)"] = 500
        map["(10, 5, 5)"] = 600
    }

    @Test
    fun plocTestGreaterOrEqual() {
        val result = map.ploc[">=1"]
        assertEquals(hashMapOf("1" to 10, "2" to 20, "3" to 30), result)
    }

    @Test
    fun plocTestLess() {
        val result = map.ploc["<3"]
        assertEquals(hashMapOf("1" to 10, "2" to 20), result)
    }

    @Test
    fun plocTestDoubleGreater() {
        val result = map.ploc[">0, >0"]
        assertEquals(hashMapOf("(1, 5)" to 100, "(5, 5)" to 200, "(10, 5)" to 300), result)
    }

    @Test
    fun plocTestGreaterOrEqualMixed() {
        val result = map.ploc[">=10, >0"]
        assertEquals(hashMapOf("(10, 5)" to 300), result)
    }

    @Test
    fun plocTestComplexCondition() {
        val result = map.ploc["<5, >=5, >=3"]
        assertEquals(hashMapOf("(1, 5, 3)" to 400), result)
    }

    @Test
    fun parseTestGreaterThan() {
        val result = map.parse("2", ">3")
        assertFalse(result)
    }

    @Test
    fun parseTestGreaterOrEqual() {
        val result = map.parse("3", ">=3")
        assertTrue(result)
    }

    @Test
    fun parseTestLessThan() {
        val result = map.parse("1", "<2")
        assertTrue(result)
    }

    @Test
    fun parseTestLessThanOrEqual() {
        val result = map.parse("5", "<=5")
        assertTrue(result)
    }

    @Test
    fun parseTestEqual() {
        val result = map.parse("4", "=4")
        assertTrue(result)
    }

    @Test
    fun parseTestNotEqual() {
        val result = map.parse("2", "<>2")
        assertFalse(result)
    }
}
