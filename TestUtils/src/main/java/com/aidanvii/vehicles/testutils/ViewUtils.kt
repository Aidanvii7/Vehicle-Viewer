package com.aidanvii.vehicles.testutils

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.view.View
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.atMost
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import de.jodamob.reflect.SuperReflect

class DummyViewTags {

    private val dummyViewTags = mutableMapOf<Int, Any>()

    @Suppress("UNCHECKED_CAST")
    operator fun <T : Any> get(key: Int): T? = dummyViewTags[key] as T?

    operator fun <T : Any> set(key: Int, element: T?) {
        if (element != null) {
            dummyViewTags[key] = element
        } else dummyViewTags.remove(key)
    }

    fun clear() {
        dummyViewTags.clear()
    }
}

fun <V : View> V.prepareMockForTest(
    context: Context? = null,
    resources: Resources? = null,
    dummyViewTags: DummyViewTags? = null
): V = if (isMock()) this.apply {
    whenever(this.context).thenReturn(context ?: mock())
    whenever(this.resources).thenReturn(resources ?: mock())
    whenever(post(any())).thenAnswer { invocation -> (invocation.arguments[0] as java.lang.Runnable).run(); true }
    prepareWithDummyViewTags(dummyViewTags ?: DummyViewTags())
} else throw IllegalStateException("${this::class.java.simpleName} is not a mock!")

private fun <V : View> V.prepareWithDummyViewTags(dummyViewTags: DummyViewTags) {
    SuperReflect.on(Build.VERSION::class.java).set("SDK_INT", Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    whenever(getTag(any())).thenAnswer { invocation ->
        dummyViewTags[(invocation.arguments[0] as Int)]
    }
    whenever(setTag(any(), any())).thenAnswer { invocation ->
        dummyViewTags[(invocation.arguments[0] as Int)] = (invocation.arguments[1])
        Unit
    }
}

fun View.verifyNoMoreRealInteractions() {
    verify(this, atMost(Int.MAX_VALUE)).getTag(any())
    verify(this, atMost(Int.MAX_VALUE)).setTag(any(), anyNullable())
    verifyNoMoreInteractions(this)
}