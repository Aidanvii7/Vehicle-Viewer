package com.aidanvii.vehicles.features.overview.domain

import com.aidanvii.vehicles.models.Vehicle
import com.aidanvii.vehicles.repository.VehicleRepository

internal class FetchVehicleUseCase(
    private val vehicleRepository: VehicleRepository
) {
    suspend fun invoke(): Vehicle = vehicleRepository.vehicle()
}