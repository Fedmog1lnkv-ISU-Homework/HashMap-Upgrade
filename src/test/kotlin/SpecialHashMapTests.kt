package SpecialHashMap

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class SpecialHashMapTest {

    @Test
    fun ploc_with_wrong_operator() {
        val specialHashMap = SpecialHashMap()
        specialHashMap["value1"] = 1
        specialHashMap["value2"] = 2

        val exception = assertThrows(Exception::class.java) {
            specialHashMap.ploc["!=1"]
        }

        assert(exception.message == "Wrong operator")
    }

    @Test
    fun ploc_with_invalid_number() {
        val specialHashMap = SpecialHashMap()
        specialHashMap["value1"] = 1

        val exception = assertThrows(Exception::class.java) {
            specialHashMap.ploc["="]
        }

        assert(exception.message == "Invalid number")
    }
}
