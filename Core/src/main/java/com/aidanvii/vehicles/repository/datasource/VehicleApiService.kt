package com.aidanvii.vehicles.repository.datasource

import com.aidanvii.vehicles.entities.ApiVehicle
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

internal interface VehicleApiService {
    // TODO pass vehicle ID from UI via some text input
    @get:GET("svc/a/270749662")
    val vehicle: Deferred<ApiVehicle>
}