package com.aidanvii.vehicles.models

data class VehicleImage(
    val baseImageUri: String
) {
    val smallImageUrl: String get() = "https://${baseImageUri}_2.jpg"
    val largeImageUrl: String get() = "https://${baseImageUri}_27.jpg"
}