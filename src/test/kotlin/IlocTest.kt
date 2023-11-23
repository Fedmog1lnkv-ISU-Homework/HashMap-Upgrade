package SpecialHashMap

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.Assertions.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class IlocTest {

    private val map = SpecialHashMap()

    @BeforeAll
    fun beforeAll() {
        map["value1"] = 1
        map["value2"] = 2
        map["value3"] = 3
        map["1"] = 10
        map["2"] = 20
        map["3"] = 30
        map["1, 5"] = 100
        map["5, 5"] = 200
        map["10, 5"] = 300
    }

    @Test
    fun ilocTestPositiveIndices() {
        assertEquals(10, map.iloc[0])
        assertEquals(300, map.iloc[2])
        assertEquals(200, map.iloc[5])
        assertEquals(3, map.iloc[8])
    }

    @Test
    fun ilocTestOutOfBoundsIndices() {
        assertNull(map.iloc[1000])
        assertNull(map.iloc[-1])
    }

    @Test
    fun ilocTestEmptyMap() {
        val emptyMap = SpecialHashMap()
        assertNull(emptyMap.iloc[0])
    }

    @Test
    fun ilocTestDuplicateIndices() {
        val mapWithDuplicates = SpecialHashMap()
        mapWithDuplicates["1"] = 10
        mapWithDuplicates["1"] = 20
        assertEquals(20, mapWithDuplicates.iloc[0])
    }
}
