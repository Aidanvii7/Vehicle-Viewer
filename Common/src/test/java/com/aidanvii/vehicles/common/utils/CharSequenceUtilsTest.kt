package com.aidanvii.vehicles.common.utils

import org.amshove.kluent.`should be false`
import org.amshove.kluent.`should be true`
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.contracts.ExperimentalContracts

@ExperimentalContracts
internal class CharSequenceUtilsTest {

    @Nested
    inner class `When CharSequence is null` {

        @Test
        fun `isNotNullAndNotEmpty returns false`() {
            null.isNotNullAndNotEmpty().`should be false`()
        }
    }

    @Nested
    inner class `When CharSequence is empty` {

        @Test
        fun `isNotNullAndNotEmpty returns false`() {
            "".isNotNullAndNotEmpty().`should be false`()
        }
    }

    @Nested
    inner class `When CharSequence is  not empty` {

        @Test
        fun `isNotNullAndNotEmpty returns true`() {
            "(ツ)".isNotNullAndNotEmpty().`should be true`()
        }
    }
}