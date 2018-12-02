package com.aidanvii.vehicles.features.overview.di

import com.aidanvii.vehicles.repository.VehicleRepository
import com.aidanvii.vehicles.features.overview.presentation.VehicleOverviewViewModel
import com.aidanvii.toolbox.Provider
import com.aidanvii.vehicles.features.overview.domain.FetchVehicleUseCase

class VehicleOverviewModule(
    private val vehicleRepositoryProvider: Provider<VehicleRepository>
) {
    internal val vehicleOverviewViewModelFactory: VehicleOverviewViewModel.Factory by lazy {
        VehicleOverviewViewModel.Factory(FetchVehicleUseCase(vehicleRepositoryProvider()))
    }
}