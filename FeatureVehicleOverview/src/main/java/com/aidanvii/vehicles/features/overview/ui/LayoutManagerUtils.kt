package com.aidanvii.vehicles.features.overview.ui

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

inline class Orientation(val orientation: Int) {
    companion object {
        val VERTICAL = Orientation(RecyclerView.VERTICAL)
        val HORIZONTAL = Orientation(RecyclerView.HORIZONTAL)
    }
}

fun createGridLayoutManager(
    context: Context,
    spanCount: Int,
    orientation: Orientation = Orientation.VERTICAL,
    reverseLayout: Boolean = false
) = GridLayoutManager(context, spanCount, orientation.orientation, reverseLayout)