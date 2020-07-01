package com.mpierucci.lasttraintolondon.lines.presentation.fluent.aserrtions

import com.mpierucci.lasttraintolondon.ristretto.fluent.Then

val Then.userSees
    get() = UserAssertions

object UserAssertions {
    fun lineStatusScreen() = LineStatusAssertions
}