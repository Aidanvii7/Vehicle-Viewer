package com.aidanvii.vehicles.databinding

import android.os.Build
import android.view.View
import androidx.annotation.IdRes
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.aidanvii.vehicles.R
import com.aidanvii.vehicles.common.databinding.OnClickBindingAdapters
import com.aidanvii.vehicles.features.overview.presentation.VehicleOverviewFragmentDirections

class AppOnClickBindingAdapters : OnClickBindingAdapters() {
    override fun View.onCLickWith(
        onClickUri: String,
        @IdRes onClickUriId: Int
    ) {
        when (onClickUriId) {
            R.id.card_vehicle -> navigateFromOverviewToDetail(onClickUri)
            else -> throwUnknownId(onClickUriId)
        }
    }

    private fun View.navigateFromOverviewToDetail(onClickUri: String) {
        val directions = VehicleOverviewFragmentDirections.actionDestinationOverviewToDestinationDetail().apply {
            setBaseImageUri(onClickUri)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val extras = FragmentNavigatorExtras(this to "transition")
            findNavController().navigate(directions, extras)
        } else {
            findNavController().navigate(directions)
        }
    }

    fun throwUnknownId(onClickUriId: Int): Nothing = throw IllegalStateException("unknown ID: $onClickUriId")
}