package com.mpierucci.lasttraintolondon.core.presentation

import com.mpierucci.android.architecture.usecase.failure.Failure

sealed class ViewContract<out T> {
    data class Success<T>(val result: T) : ViewContract<T>()
    data class Error(val failure: Failure) : ViewContract<Nothing>()
    data class Loading(val loading: Boolean) : ViewContract<Nothing>()
}