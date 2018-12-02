package com.aidanvii.vehicles.common.utils

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@ExperimentalContracts
fun Any?.isNotNull(): Boolean {
    contract { returns(true) implies (this@isNotNull != null) }
    return this != null
}