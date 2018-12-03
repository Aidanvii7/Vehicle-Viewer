package com.aidanvii.vehicles.repository

import com.aidanvii.vehicles.models.Vehicle
import kotlinx.coroutines.CoroutineScope

interface VehicleRepository {
    suspend fun CoroutineScope.vehicle(): Vehicle
}