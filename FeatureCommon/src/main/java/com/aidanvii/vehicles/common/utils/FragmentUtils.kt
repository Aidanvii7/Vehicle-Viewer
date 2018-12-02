package com.aidanvii.vehicles.common.utils

import androidx.fragment.app.Fragment


inline fun <reified T> Fragment.appAs(): T = context!!.applicationContext as T