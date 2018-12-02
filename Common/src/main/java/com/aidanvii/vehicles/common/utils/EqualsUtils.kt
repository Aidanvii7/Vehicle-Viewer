package com.aidanvii.vehicles.common.utils

import kotlin.reflect.KClass

inline fun <reified T : Any> T.areEqual(
    other: Any?,
    areEqual: T.(other: T) -> Boolean
): Boolean = when {
    other == null -> false
    this === other -> true
    other is T && other instanceOf this::class -> areEqual(other)
    else -> false
}

infix fun Any.instanceOf(classType: KClass<*>) = this::class == classType