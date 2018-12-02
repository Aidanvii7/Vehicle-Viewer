package com.aidanvii.vehicles.main.databinding

import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import com.aidanvii.vehicles.common.databinding.ImageViewBindingAdapters
import com.aidanvii.vehicles.common.databinding.OnClickBindingAdapters

data class DataBindingComponent(
    private val imageViewBindingAdapters: ImageViewBindingAdapters,
    private val onClickBindingAdapters: OnClickBindingAdapters
) : DataBindingComponent {
    fun makeDefaultComponent() {
        DataBindingUtil.setDefaultComponent(this)
    }
    override fun getImageViewBindingAdapters() = imageViewBindingAdapters
    override fun getOnClickBindingAdapters() = onClickBindingAdapters
}