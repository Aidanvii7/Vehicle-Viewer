package com.aidanvii.vehicles.common.databinding

import android.view.View
import androidx.annotation.IdRes
import androidx.databinding.BindingAdapter

abstract class OnClickBindingAdapters {

    @BindingAdapter(
        "onClickUri",
        "onClickUriId", requireAll = true
    )
    fun View.bindOnClickUri(
        onClickUri: String?,
        @IdRes onClickUriId: Int
    ) {
        setOnClickListener(
            onClickUri?.let {
                View.OnClickListener { onCLickWith(onClickUri, onClickUriId) }
            }
        )
    }

    abstract fun View.onCLickWith(
        onClickUri: String,
        @IdRes onClickUriId: Int
    )
}