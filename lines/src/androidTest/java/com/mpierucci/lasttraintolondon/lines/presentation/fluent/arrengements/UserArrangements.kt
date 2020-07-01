package com.mpierucci.lasttraintolondon.lines.presentation.fluent.arrengements

import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import com.mpierucci.lasttraintolondon.lines.R
import com.mpierucci.lasttraintolondon.lines.ui.LineStatusScreen
import com.mpierucci.lasttraintolondon.ristretto.fluent.Given

val Given.user
    get() = UserArrangements

object UserArrangements {

    fun launchLineStatusFragment(fragmentFactory: FragmentFactory) {
        launchFragmentInContainer<LineStatusScreen>(
            factory = fragmentFactory,
            themeResId = R.style.Theme_MaterialComponents // Theme needed since you are testing fragment in isolation
        )
    }
}