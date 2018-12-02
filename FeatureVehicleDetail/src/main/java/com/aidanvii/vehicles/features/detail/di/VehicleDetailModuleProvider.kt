package com.aidanvii.vehicles.features.detail.di

import android.os.Bundle


interface VehicleDetailModuleProvider {
    fun vehicleDetailModule(bundle: Bundle): VehicleDetailModule
}