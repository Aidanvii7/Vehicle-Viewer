package com.aidanvii.vehicles.features.overview.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aidanvii.vehicles.features.overview.di.VehicleOverviewModuleProvider
import com.aidanvii.vehicles.common.utils.appAs
import com.aidanvii.toolbox.arch.viewmodel.ViewModelFactory
import com.aidanvii.toolbox.arch.viewmodel.addTypedFactory
import com.aidanvii.toolbox.arch.viewmodel.viewModelProvider
import com.aidanvii.vehicles.features.overview.databinding.FragmentVehicleOverviewBinding

class VehicleOverviewFragment : Fragment() {

    private val factory: ViewModelFactory by lazy {
        ViewModelFactory.Builder()
            .addTypedFactory(overviewViewModelFactory)
            .build()
    }

    private val overviewViewModelFactory: VehicleOverviewViewModel.Factory
        get() = appAs<VehicleOverviewModuleProvider>()
            .vehicleOverviewModule
            .vehicleOverviewViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentVehicleOverviewBinding.inflate(inflater, container, false).run {
        viewModel = activity!!.viewModelProvider(factory)[VehicleOverviewViewModel::class.java]
        root
    }
}