package com.aidanvii.vehicles.features.overview.presentation

import com.aidanvii.vehicles.models.VehicleImage

internal class VehicleImageViewModel(
    private val vehicleImage: VehicleImage
) {
    val imageUrl: String get() = vehicleImage.smallImageUrl
    val baseImageUri: String get() = vehicleImage.baseImageUri
}