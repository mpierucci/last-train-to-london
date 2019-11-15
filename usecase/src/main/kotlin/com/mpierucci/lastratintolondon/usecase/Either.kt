package com.mpierucci.lastratintolondon.usecase

// TODO investigate Arrow
sealed class Either<out L, out R> {
    data class Left<L>(val value: L) : Either<L, Nothing>()
    data class Right<R>(val value: R) : Either<Nothing, R>()

    fun either(lefFunc: (L) -> Any, rightFunc: (R) -> Any): Any {
        return when (this) {
            is Left -> lefFunc(value)
            is Right -> rightFunc(value)
        }
    }
}