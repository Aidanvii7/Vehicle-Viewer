package com.aidanvii.vehicles.repository

import com.aidanvii.vehicles.mappers.toVehicle
import com.aidanvii.vehicles.models.Vehicle
import com.aidanvii.vehicles.repository.datasource.VehicleApiService

internal class VehicleRepositoryImpl(
    private val apiService: VehicleApiService
) : VehicleRepository {

    override suspend fun vehicle(): Vehicle =
        apiService.vehicle.await().toVehicle()
}