package com.mpierucci.lasttraintolondon.core.view

import android.view.View

// Visibility region
fun View.visibleIfNot() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.goneIfNot() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}
// end of visibility region