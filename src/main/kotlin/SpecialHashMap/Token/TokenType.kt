package SpecialHashMap.Token

sealed class TokenType {
    sealed class Operator(val operator: String) : TokenType() {
        object GreaterThan : Operator(">")
        object LessThan : Operator("<")
        object GreaterThanOrEqual : Operator(">=")
        object LessThanOrEqual : Operator("<=")
        object Equal : Operator("=")
        object NotEqual : Operator("<>")
    }

    object Index : TokenType()
    object EndOfStatement : TokenType()
}
