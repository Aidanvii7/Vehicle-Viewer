package com.aidanvii.vehicles.entities

import com.squareup.moshi.Json

internal data class ApiVehicle(
    @Json(name = "images")
    val images: List<ApiVehicleImage>
)