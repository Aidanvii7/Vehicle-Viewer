package com.aidanvii.vehicles.common.databinding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("onRefresh")
internal fun SwipeRefreshLayout.bind(onRefreshListener: SwipeRefreshLayout.OnRefreshListener?) {
    setOnRefreshListener(onRefreshListener)
}

@BindingAdapter("refreshing")
internal fun SwipeRefreshLayout.bind(refreshing: Boolean?) {
    refreshing?.let { isRefreshing = it }
}