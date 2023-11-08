package com.example.custom_slider_ui.decoration

import android.graphics.Rect
import android.view.View
import androidx.annotation.Px
import androidx.recyclerview.widget.RecyclerView

class LinearDecoration(@Px private val innerSpacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val itemPos = parent.getChildAdapterPosition(view)
        outRect.left = if(itemPos == 0 ) 0 else innerSpacing/2
        outRect.right = if (itemPos == state.itemCount - 1) 0 else innerSpacing/2
    }
}