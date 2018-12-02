package com.aidanvii.vehicles.testutils

import com.nhaarman.mockitokotlin2.spy
import org.mockito.Mockito
import org.mockito.internal.util.MockUtil

fun Any.isMock() = MockUtil.isMock(this)

fun Any.isSpy() = MockUtil.isSpy(this)

inline fun <reified T> anyNullable() = Mockito.nullable(T::class.java)

inline fun <reified T : Any> T.spied() = if (isSpy()) this else spy(this)