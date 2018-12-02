package com.aidanvii.vehicles.features.detail.presentation

import com.aidanvii.vehicles.models.VehicleImage

internal class VehicleImageViewModel(
    private val vehicleImage: VehicleImage
) {
    val imageUrl: String get() = vehicleImage.largeImageUrl
}