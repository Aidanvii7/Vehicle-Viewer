package com.aidanvii.vehicles.mappers

import com.aidanvii.vehicles.common.utils.logger.logD
import com.aidanvii.vehicles.entities.ApiVehicle
import com.aidanvii.vehicles.models.Vehicle
import com.aidanvii.vehicles.models.VehicleImage

internal fun ApiVehicle.toVehicle() = Vehicle(
    images.mapNotNull { apiVehicleImage ->
        apiVehicleImage.uri
    }.map { imageUri ->
        VehicleImage(imageUri)
    }.also {
        logD("toVehicle thread: ${Thread.currentThread().name}")
    }
)