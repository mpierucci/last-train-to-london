package com.mpierucci.lasttraintolondon.lines.presentation

import com.mpierucci.lasttraintolondon.core.failure.Failure


internal sealed class LinesViewState {
    data class Success(val result: List<PresentationLineStatus>) : LinesViewState()
    data class Error(val failure: Failure) : LinesViewState()
    object Loading : LinesViewState()
}