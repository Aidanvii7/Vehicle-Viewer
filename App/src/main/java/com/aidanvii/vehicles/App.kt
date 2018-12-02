package com.aidanvii.vehicles

import android.app.Application
import android.os.Bundle
import com.aidanvii.vehicles.main.databinding.GlideImageViewBindingAdapters
import com.aidanvii.vehicles.repository.VehicleRepositoryModule
import com.aidanvii.vehicles.common.utils.logger.AndroidLogger
import com.aidanvii.vehicles.common.utils.logger.CompositeLogger
import com.aidanvii.vehicles.features.detail.di.VehicleDetailModule
import com.aidanvii.vehicles.features.detail.di.VehicleDetailModuleProvider
import com.aidanvii.vehicles.features.detail.presentation.VehicleDetailFragmentArgs
import com.aidanvii.vehicles.features.overview.databinding.AppOnClickBindingAdapters
import com.aidanvii.vehicles.features.overview.di.VehicleOverviewModule
import com.aidanvii.vehicles.features.overview.di.VehicleOverviewModuleProvider
import com.aidanvii.vehicles.main.databinding.DataBindingComponent
import com.aidanvii.vehicles.models.VehicleImage

class App :
    Application(),
    VehicleOverviewModuleProvider,
    VehicleDetailModuleProvider {
    lateinit var defaultDataBindingComponent: DataBindingComponent

    override fun onCreate() {
        super.onCreate()
        defaultDataBindingComponent = DataBindingComponent(
            imageViewBindingAdapters = GlideImageViewBindingAdapters(),
            onClickBindingAdapters = AppOnClickBindingAdapters()
        ).apply { makeDefaultComponent() }
        CompositeLogger += AndroidLogger("Aidan")
    }

    override val vehicleOverviewModule: VehicleOverviewModule by lazy {
        VehicleOverviewModule(
            vehicleRepositoryProvider = VehicleRepositoryModule()
        )
    }

    override fun vehicleDetailModule(bundle: Bundle) = VehicleDetailModule(
        vehicleImage = VehicleImage(
            baseImageUri = VehicleDetailFragmentArgs.fromBundle(bundle).baseImageUri
        )
    )
}