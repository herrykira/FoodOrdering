package com.example.kinhangpoon.foodordering.utility

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by hefen on 3/16/2018.
 */

class ItemDecoration(private val startPadding: Int, private val endPadding: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {

        val totalWidth = parent.width

        //first element
        if (parent.getChildAdapterPosition(view) == 0) {
            var firstPadding = (totalWidth - startPadding) / 2
            firstPadding = Math.max(0, firstPadding)
            outRect.set(firstPadding, 0, 0, 0)
        }

        //last element
        if (parent.getChildAdapterPosition(view) == parent.adapter.itemCount - 1 && parent.adapter.itemCount > 1) {
            var lastPadding = (totalWidth - endPadding) / 2
            lastPadding = Math.max(0, lastPadding)
            outRect.set(0, 0, lastPadding, 0)
        }
    }

}
