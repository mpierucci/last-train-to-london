package com.mpierucci.lasttraintolondon.core.edgetoede

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding

fun View.accountStatusBarSpace() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        v.updatePadding(top = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top)
        insets
    }
}