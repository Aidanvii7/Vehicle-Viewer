package com.aidanvii.vehicles.main.databinding

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.VisibleForTesting
import com.aidanvii.toolbox.Provider
import com.aidanvii.vehicles.common.databinding.ImageViewBindingAdapters
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory

typealias GlideWith = (context: Context) -> RequestManager

class GlideImageViewBindingAdapters(
    @param:VisibleForTesting
    private val glideWith: GlideWith = { Glide.with(it) },
    @param:VisibleForTesting
    private val requestOptions: Provider<RequestOptions> = { RequestOptions() }
) : ImageViewBindingAdapters() {

    private val crossFadeFactory = DrawableCrossFadeFactory
        .Builder()
        .setCrossFadeEnabled(true)
        .build()

    override fun ImageView.loadImage(imageUrl: String) {
        glideWith(context)
            .load(imageUrl)
            .applyTransformations()
            .into(this)
    }

    override fun ImageView.cancelPendingRequest() {
        glideWith(context).clear(this)
    }

    private fun RequestBuilder<Drawable>.applyTransformations(): RequestBuilder<Drawable> =
        applyTransition().apply(requestOptions().diskCacheStrategy(DiskCacheStrategy.DATA))

    private fun RequestBuilder<Drawable>.applyTransition(): RequestBuilder<Drawable> =
        transition(withCrossFade(crossFadeFactory))
}