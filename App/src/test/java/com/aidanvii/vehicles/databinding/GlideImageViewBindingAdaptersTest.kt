package com.aidanvii.vehicles.databinding

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.aidanvii.vehicles.testutils.prepareMockForTest
import com.aidanvii.vehicles.testutils.verifyNoMoreRealInteractions
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.contracts.ExperimentalContracts

@ExperimentalContracts
class GlideImageViewBindingAdaptersTest {

    val requestBuilder = mock<RequestBuilder<Drawable>>().apply {
        whenever(transition(any())).thenReturn(this)
        whenever(apply(any())).thenReturn(this)
    }

    val requestManager = mock<RequestManager>().apply {
        whenever(load(any<String>())).thenReturn(requestBuilder)
    }

    val requestOptions = mock<RequestOptions>().apply {
        whenever(diskCacheStrategy(any())).thenReturn(this)
    }

    private val tested = GlideImageViewBindingAdapters(
        glideWith = { requestManager },
        requestOptions = { requestOptions }
    )

    val activity = mock<AppCompatActivity>()
    val resources = mock<Resources>()
    val imageView = mock<ImageView>().prepareMockForTest(
        context = activity,
        resources = resources
    )

    var givenImageUrl: String? = null

    private fun ImageView.invokeWithParams() {
        tested.apply {
            _bind(
                imageUrl = givenImageUrl
            )
        }
    }

    @Nested
    inner class `when imageUrl is null` {

        @BeforeEach
        fun givenWhen() {
            givenImageUrl = null
            imageView.invokeWithParams()
        }

        @Test
        fun `nothing happens`() {
            imageView.verifyNoMoreRealInteractions()
        }
    }

    @Nested
    inner class `when imageUrl is empty` {

        @BeforeEach
        fun givenWhen() {
            givenImageUrl = ""
            imageView.invokeWithParams()
        }

        @Test
        fun `nothing happens`() {
            imageView.verifyNoMoreRealInteractions()
        }
    }

    @Nested
    inner class `when imageUrl is not empty` {

        val nonNullImageUrl = "nonNullImageUrl"

        @BeforeEach
        fun givenWhen() {
            givenImageUrl = nonNullImageUrl
        }

        @Nested
        inner class `when invoked` {

            @BeforeEach
            fun givenWhen() {
                imageView.invokeWithParams()
            }

            @Test
            fun `loads image url with correct configuration`() {
                inOrder(requestManager, requestBuilder, requestOptions).apply {
                    verify(requestManager).load("nonNullImageUrl")
                    verify(requestBuilder).transition(any())
                    verify(requestOptions).diskCacheStrategy(DiskCacheStrategy.DATA)
                    verify(requestBuilder).into(imageView)
                    verifyNoMoreInteractions()
                }
            }
        }

        @Nested
        inner class `when imageUrl is null while an in-flight request is in progress` {

            @BeforeEach
            fun givenWhen() {
                givenImageUrl = nonNullImageUrl
                imageView.invokeWithParams()
                givenImageUrl = null
                imageView.invokeWithParams()
            }

            @Test
            fun `pending request is cancelled`() {
                verify(requestManager).clear(imageView)
            }

            @Test
            fun `sets drawable as null`() {
                verify(imageView).setImageDrawable(null)
            }
        }
    }
}