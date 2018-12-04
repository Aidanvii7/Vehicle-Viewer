package com.aidanvii.vehicles.features.overview.presentation

import com.aidanvii.vehicles.features.overview.domain.FetchVehicleUseCase
import com.aidanvii.vehicles.models.Vehicle
import com.aidanvii.vehicles.models.VehicleImage
import com.aidanvii.vehicles.repository.VehicleRepository
import com.aidanvii.vehicles.testutils.spied
import com.aidanvii.vehicles.testutils.synchronousCoroutineDispatchers
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verifyBlocking
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be false`
import org.amshove.kluent.`should be true`
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should equal`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
internal class VehicleOverviewViewModelTest {

    val vehicle = Vehicle(
        images = listOf(
            VehicleImage("1"),
            VehicleImage("2"),
            VehicleImage("3")
        )
    )

    val repository = object : VehicleRepository {
        override suspend fun fetchVehicleIn(coroutineScope: CoroutineScope): Vehicle = vehicle
    }

    val fetchVehicleUseCase = FetchVehicleUseCase(repository)

    @Nested
    inner class `When view model is initialised` {

        val tested = VehicleOverviewViewModel(
            fetchVehicleUseCase = fetchVehicleUseCase,
            coroutineDispatchers = synchronousCoroutineDispatchers
        )

        @Test
        fun `showLoader is true`() {
            tested.showLoader.`should be true`()
        }

        @Test
        fun `vehicleAdapterItems contains 6 placeholder adapter items`() {
            tested.vehicleAdapterItems.apply {
                size `should be equal to` 6
                forEach { it `should be` VehicleImageAdapterItem.PLACEHOLDER }
            }
        }

        @Nested
        inner class `When init is called` {

            @BeforeEach
            fun givenWhen() = tested.init()

            @Test
            fun `showLoader is false`() {
                tested.showLoader.`should be false`()
            }

            @Test
            fun `vehicleAdapterItems contains 3 adapter items with the expected VehicleImages`() {
                tested.vehicleAdapterItems.apply {
                    size `should be equal to` 3
                    (0..2).forEach { index ->
                        this[index] `should equal` VehicleImageAdapterItem(vehicle.images[index])
                    }
                }
            }
        }
    }
}