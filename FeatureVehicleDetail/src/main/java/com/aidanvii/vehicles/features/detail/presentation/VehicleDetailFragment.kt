package com.aidanvii.vehicles.features.detail.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.aidanvii.vehicles.common.utils.appAs
import com.aidanvii.vehicles.features.detail.databinding.FragmentVehicleDetailBinding
import com.aidanvii.vehicles.features.detail.di.VehicleDetailModuleProvider

class VehicleDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentVehicleDetailBinding.inflate(inflater, container, false).run {
        arguments?.let { arguments ->
            viewModel = VehicleImageViewModel(
                vehicleImage = appAs<VehicleDetailModuleProvider>().vehicleDetailModule(arguments).vehicleImage
            )
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
            sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }
        root
    }
}