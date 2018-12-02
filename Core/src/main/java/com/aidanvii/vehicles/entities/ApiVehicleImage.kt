package com.aidanvii.vehicles.entities

import com.squareup.moshi.Json

internal data class ApiVehicleImage(
    @Json(name = "uri")
    val uri: String?
)