package com.mpierucci.lasttraintolondon.ristretto.assertions

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers

/**
 * [ViewAssertion] to assert items int a [RecyclerView]
 *
 * @param expectedCount, expected item quantity
 */
class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        noViewFoundException?.let { throw it }

        if (view !is RecyclerView) throw IllegalStateException("Asserted view $view is not a RecyclerView")

        val adapter = view.adapter
            ?: throw IllegalStateException("Recycler view has not adapter")

        ViewMatchers.assertThat(adapter.itemCount, CoreMatchers.equalTo(expectedCount))
    }
}