package com.aidanvii.vehicles.common.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.aidanvii.vehicles.common.utils.isNotNullAndNotEmpty
import com.aidanvii.toolbox.databinding.getTrackedValue
import com.aidanvii.toolbox.databinding.trackValue
import com.aidanvii.vehicles.features.common.R
import kotlin.contracts.ExperimentalContracts

abstract class ImageViewBindingAdapters {

    @BindingAdapter("imageUrl")
    @ExperimentalContracts
    fun ImageView._bind(imageUrl: String?) {
        trackValue(
            newValue = imageUrlFor(imageUrl),
            valueResId = R.id.image_binding_params,
            onNewValue = { loadImage(it) },
            onOldValue = {
                cancelPendingRequest()
                if(imageUrl == null) setImageDrawable(null)
            }
        )
    }

    @ExperimentalContracts
    private fun imageUrlFor(imageUrl: String?): String? =
        if (imageUrl.isNotNullAndNotEmpty()) imageUrl else null

    protected abstract fun ImageView.loadImage(imageUrl: String)

    protected abstract fun ImageView.cancelPendingRequest()
}