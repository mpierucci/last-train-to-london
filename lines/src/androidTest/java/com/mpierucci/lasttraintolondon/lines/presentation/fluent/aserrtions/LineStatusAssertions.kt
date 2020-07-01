package com.mpierucci.lasttraintolondon.lines.presentation.fluent.aserrtions

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.mpierucci.lasttraintolondon.lines.R
import com.mpierucci.lasttraintolondon.ristretto.assertions.RecyclerViewItemCountAssertion
import org.hamcrest.core.IsNot.not

object LineStatusAssertions {
    init {
        onView(withId(R.id.linesStatus))
            .check(matches(isDisplayed()))


        onView(withId(R.id.errorGroup))
            .check(matches(not(isDisplayed())))
    }

    fun withLinesDisplayed(quantity: Int) = apply {
        onView(withId(R.id.linesStatus))
            .check(RecyclerViewItemCountAssertion(quantity))
    }
}