package com.aidanvii.vehicles.features.overview.presentation

import com.aidanvii.vehicles.common.utils.unsafeLazy
import com.aidanvii.toolbox.adapterviews.databinding.BindableAdapterItem
import com.aidanvii.vehicles.features.overview.R
import com.aidanvii.vehicles.models.VehicleImage
import com.aidanvii.vehicles.common.utils.areEqual

internal sealed class VehicleImageAdapterItem : BindableAdapterItem {

    private data class Populated(private val vehicleImage: VehicleImage) : VehicleImageAdapterItem() {
        override val layoutId get() = R.layout.card_vehicle_image
        override val lazyBindableItem = unsafeLazy { VehicleImageViewModel(vehicleImage) }
        override val isEmpty get() = false
    }

    private object Placeholder : VehicleImageAdapterItem() {
        override val layoutId get() = R.layout.card_vehicle_image_placeholder
        override val lazyBindableItem = unsafeLazy { null }
        override val isEmpty get() = true
    }

    companion object {
        fun buildWith(vehicleImage: VehicleImage?): VehicleImageAdapterItem =
            vehicleImage?.let { Populated(vehicleImage) } ?: Placeholder
    }
}