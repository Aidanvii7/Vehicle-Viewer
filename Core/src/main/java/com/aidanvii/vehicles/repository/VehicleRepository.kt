package com.aidanvii.vehicles.repository

import com.aidanvii.vehicles.models.Vehicle

interface VehicleRepository {
    suspend fun vehicle(): Vehicle
}