package com.aidanvii.vehicles.features.overview.di

import androidx.databinding.DataBindingComponent
import com.aidanvii.vehicles.features.overview.presentation.VehicleOverviewFragment

interface VehicleOverviewModuleProvider {
    val vehicleOverviewModule: VehicleOverviewModule
}