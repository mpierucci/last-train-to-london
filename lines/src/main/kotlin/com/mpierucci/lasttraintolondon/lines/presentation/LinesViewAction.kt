package com.mpierucci.lasttraintolondon.lines.presentation

sealed class LinesViewAction {
    object FetchStatus : LinesViewAction()
}