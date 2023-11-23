package SpecialHashMap

import SpecialHashMap.Token.TokenType
import java.lang.Exception

class SpecialHashMap : HashMap<String, Int>() {

    interface Loc<K, V> {
        operator fun get(index: K): V
    }

    private inline fun <K, V> createLoc(crossinline onGet: (K) -> V): Loc<K, V> = object : Loc<K, V> {
        override fun get(index: K): V = onGet(index)
    }

    val iloc = createLoc<Int, Int?> { index ->
        val sortedKeys = keys.sorted().toList()
        if (index < 0 || index >= sortedKeys.size) null else this@SpecialHashMap[sortedKeys[index]]
    }

    val ploc = createLoc<String, HashMap<String, Int>> { condition ->

        fun checkConditions(subKeys: List<String>, conditions: List<String>): Boolean {
            return subKeys.zip(conditions).all { (subKey, condition) -> parse(subKey, condition) }
        }

        val conditions: List<String> = condition.split(",").map { it.trim() }
        val result = SpecialHashMap()

        for (key in keys) {
            val subKeys = key.trim('(', ')').split(",").map { it.trim() }

            if (subKeys.size == conditions.size && checkConditions(subKeys, conditions)) {
                result[key] = this@SpecialHashMap[key] ?: 0
            }
        }

        result
    }

    fun parse(key: String, condition: String): Boolean {
        val parser = Parser(condition)

        val operator = parser.getNextToken().type
        val rightValue: Int? = parser.getNextToken().value

        if (rightValue == null) {
            throw Exception("Invalid number")
        }

        val leftValue = key.toIntOrNull() ?: return false
        return when (operator) {
            TokenType.Operator.GreaterThan -> leftValue > rightValue
            TokenType.Operator.GreaterThanOrEqual -> leftValue >= rightValue
            TokenType.Operator.LessThan -> leftValue < rightValue
            TokenType.Operator.LessThanOrEqual -> leftValue <= rightValue
            TokenType.Operator.Equal -> leftValue == rightValue
            TokenType.Operator.NotEqual -> leftValue != rightValue
            else -> throw Exception("Wrong operator")
        }
    }
}

