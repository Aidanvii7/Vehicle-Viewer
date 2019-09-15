package com.aidanvii.vehicles.features.overview.presentation

import androidx.databinding.Bindable
import com.aidanvii.toolbox.adapterviews.recyclerview.BindingRecyclerViewBinder
import com.aidanvii.toolbox.arch.viewmodel.ViewModelFactory
import com.aidanvii.toolbox.databinding.ObservableArchViewModel
import com.aidanvii.toolbox.databinding.bindable
import com.aidanvii.vehicles.common.utils.CoroutineDispatchers
import com.aidanvii.vehicles.common.utils.logger.logD
import com.aidanvii.vehicles.features.overview.domain.FetchVehicleUseCase
import com.aidanvii.vehicles.common.utils.logger.logE
import com.aidanvii.vehicles.common.utils.repeatedListOf
import com.aidanvii.vehicles.features.overview.R
import com.aidanvii.vehicles.features.overview.ui.createGridLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class VehicleOverviewViewModel(
    private val fetchVehicle: FetchVehicleUseCase,
    private val coroutineDispatchers: CoroutineDispatchers = CoroutineDispatchers.DEFAULT
) : ObservableArchViewModel() {

    class Factory(
        private val fetchVehicleUseCase: FetchVehicleUseCase
    ) : ViewModelFactory.TypedFactory<VehicleOverviewViewModel> {
        override fun create() = VehicleOverviewViewModel(fetchVehicleUseCase).apply { init() }
    }

    @get:Bindable
    var showLoader: Boolean by bindable(true)
        private set

    @get:Bindable
    var vehicleAdapterItems: List<VehicleImageAdapterItem> by bindable(
        repeatedListOf(
            element = VehicleImageAdapterItem.buildWith(null),
            count = 6
        )
    )
        private set

    val binder = BindingRecyclerViewBinder<VehicleImageAdapterItem>(
        layoutManagerFactory = { context ->
            createGridLayoutManager(
                context = context,
                spanCount = context.resources.getInteger(R.integer.overview_span_count)
            )
        },
        uiDispatcher = coroutineDispatchers.main,
        workerDispatcher = coroutineDispatchers.default
    )

    private val fetchVehicleJob = Job()

    fun init() {
        CoroutineScope(coroutineDispatchers.main + fetchVehicleJob).launch {
            (1..10).fold(initialRetryDelay) { exponentialBackoff, _ ->
                try {
                    vehicleAdapterItems = fetchAndBuildAdapterItems()
                    logD("fetchAndBuildAdapterItems: success!")
                    showLoader = false
                    return@launch
                } catch (e: Throwable) {
                    e.message?.let { logE(it) }
                }
                logD("fetchAndBuildAdapterItems: failure! trying again in $exponentialBackoff millis")
                delay(exponentialBackoff)
                exponentialBackoff + 1000
            }
            showLoader = false
        }
    }

    private suspend fun CoroutineScope.fetchAndBuildAdapterItems(): List<VehicleImageAdapterItem> =
        fetchVehicle(this).images.map { vehicleImage -> VehicleImageAdapterItem.buildWith(vehicleImage) }

    override fun onCleared() = fetchVehicleJob.cancel()

    companion object {
        private val initialRetryDelay = 500L
    }
}