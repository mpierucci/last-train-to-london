package com.mpierucci.lasttraintolondon.lines.presentation


internal sealed class LinesViewState {
    data class Success(val result: List<PresentationLineStatus>) : LinesViewState()
    data class Error(val failure: Throwable) : LinesViewState()
    object Loading : LinesViewState()
}