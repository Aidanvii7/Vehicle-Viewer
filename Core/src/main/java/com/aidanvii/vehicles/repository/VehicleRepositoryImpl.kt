package com.aidanvii.vehicles.repository

import com.aidanvii.vehicles.common.utils.CoroutineDispatchers
import com.aidanvii.vehicles.common.utils.logger.logD
import com.aidanvii.vehicles.mappers.toVehicle
import com.aidanvii.vehicles.models.Vehicle
import com.aidanvii.vehicles.repository.datasource.VehicleApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

internal class VehicleRepositoryImpl(
    private val apiService: VehicleApiService,
    private val dispatchers: CoroutineDispatchers
) : VehicleRepository {
    override suspend fun fetchVehicleIn(coroutineScope: CoroutineScope): Vehicle =
        coroutineScope.run {
            suspendCoroutine { continuation ->
                logD("vehicle thread: ${Thread.currentThread().name}")
                launch(coroutineContext + dispatchers.io) {
                    try {
                        apiService.vehicle.await().let {
                            continuation.resume(it.toVehicle())
                        }
                    } catch (e: Throwable) {
                        continuation.resumeWithException(e)
                    }
                }
            }
        }


}


