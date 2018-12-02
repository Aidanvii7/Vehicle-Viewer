package com.aidanvii.vehicles.common.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView

class AspectRatioAppCompatImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    var aspectRatio: Float = 1f
        set(value) {
            if (value != 0f) {

                field = value
            }
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = View.MeasureSpec.getSize(widthMeasureSpec)
        val height = (width / aspectRatio).toInt()
        setMeasuredDimension(width, height)
    }
}