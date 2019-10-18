package com.mpierucci.lastratintolondon.usecase

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val error: Any) : Result<T>() //TODO after adding error handling
}