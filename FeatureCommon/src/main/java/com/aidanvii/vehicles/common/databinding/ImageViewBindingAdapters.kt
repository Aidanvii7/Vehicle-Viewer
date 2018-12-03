package com.aidanvii.vehicles.common.databinding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.aidanvii.vehicles.common.utils.isNotNull
import com.aidanvii.vehicles.common.utils.isNotNullAndNotEmpty
import com.aidanvii.toolbox.databinding.getTrackedValue
import com.aidanvii.toolbox.databinding.trackValue
import com.aidanvii.vehicles.features.common.R
import kotlin.contracts.ExperimentalContracts

abstract class ImageViewBindingAdapters {

    @BindingAdapter("image")
    fun ImageView._bind(image: Int?) {
        image?.let { setImageResource(it) }
    }

    @BindingAdapter(
        "imageUrl",
        "placeHolder",
        "crossFadeEnabled", requireAll = false
    )
    @ExperimentalContracts
    fun ImageView._bind(
        imageUrl: String?,
        placeHolder: Drawable?,
        crossFadeEnabled: Boolean?
    ) {
        trackValue(
            newValue = imageBindingParamsFor(imageUrl, placeHolder, crossFadeEnabled),
            valueResId = R.id.image_binding_params,
            onNewValue = { loadImage(it) },
            onOldValue = {
                cancelPendingRequest()
                setImageDrawable(placeHolder)
            }
        )
    }

    @ExperimentalContracts
    private fun ImageView.imageBindingParamsFor(
        imageUrl: String?,
        placeHolder: Drawable?,
        crossFadeEnabled: Boolean?
    ): ImageBindingParams? = when {
        imageUrl.isNotNullAndNotEmpty() -> {
            ImageBindingParams(
                imageUrl = imageUrl,
                crossFadeEnabled = crossFadeEnabled ?: true,
                placeholder = placeHolder
            )
        }
        placeHolder.isNotNull() && notBound -> {
            null.also { setImageDrawable(placeHolder) }
        }
        else -> null
    }

    private val ImageView.notBound: Boolean
        get() = getTrackedValue<Any?>(R.id.image_binding_params) == null

    protected abstract fun ImageView.loadImage(imageViewParams: ImageBindingParams)

    protected abstract fun ImageView.cancelPendingRequest()

    protected data class ImageBindingParams(
        val imageUrl: String,
        val crossFadeEnabled: Boolean,
        val placeholder: Drawable?
    )
}