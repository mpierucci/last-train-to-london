package com.mpierucci.lasttraintolondon.lines.presentation

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class LineStatusDecorator(
    @DimenRes val offsetRes: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val offSet = view.context.resources.getDimensionPixelOffset(offsetRes)
        with(outRect) {
            if (parent.getChildAdapterPosition(view) != 0) {
                top = offSet
            }
        }
    }
}