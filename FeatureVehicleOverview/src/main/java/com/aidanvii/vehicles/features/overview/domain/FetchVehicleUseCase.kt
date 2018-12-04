package com.aidanvii.vehicles.features.overview.domain

import com.aidanvii.vehicles.models.Vehicle
import com.aidanvii.vehicles.repository.VehicleRepository
import kotlinx.coroutines.CoroutineScope

internal class FetchVehicleUseCase(
    private val vehicleRepository: VehicleRepository
) {
    suspend fun invokeIn(coroutineScope: CoroutineScope): Vehicle =
        vehicleRepository.fetchVehicleIn(coroutineScope)
}