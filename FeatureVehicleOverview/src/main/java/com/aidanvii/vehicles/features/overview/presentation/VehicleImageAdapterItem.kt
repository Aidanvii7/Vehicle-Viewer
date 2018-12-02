package com.aidanvii.vehicles.features.overview.presentation

import com.aidanvii.vehicles.common.utils.unsafeLazy
import com.aidanvii.toolbox.adapterviews.databinding.BindableAdapterItem
import com.aidanvii.vehicles.features.overview.R
import com.aidanvii.vehicles.models.VehicleImage
import com.aidanvii.vehicles.common.utils.areEqual
import com.aidanvii.vehicles.features.overview.R.layout.card_vehicle_image
import com.aidanvii.vehicles.features.overview.R.layout.card_vehicle_image_placeholder

internal data class VehicleImageAdapterItem(
    private val vehicleImage: VehicleImage?
) : BindableAdapterItem {

    override val layoutId: Int
        get() = if (vehicleImage != null) card_vehicle_image else card_vehicle_image_placeholder

    override val lazyBindableItem: Lazy<VehicleImageViewModel?> = unsafeLazy {
        vehicleImage?.let { VehicleImageViewModel(vehicleImage) }
    }

    override val isEmpty: Boolean get() = vehicleImage == null

    override fun equals(other: Any?): Boolean = areEqual(other) { vehicleImage == it.vehicleImage }

    override fun hashCode(): Int = vehicleImage.hashCode()

    companion object {
        val PLACEHOLDER = VehicleImageAdapterItem(null)
    }
}