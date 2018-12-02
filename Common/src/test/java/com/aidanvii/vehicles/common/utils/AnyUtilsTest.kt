package com.aidanvii.vehicles.common.utils

import org.amshove.kluent.`should be false`
import org.amshove.kluent.`should be true`
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.contracts.ExperimentalContracts

@ExperimentalContracts
class AnyUtilsTest {

    @Nested
    inner class `When object is null` {

        @Test
        fun `isNotNullAndNotEmpty returns false`() {
            null.isNotNull().`should be false`()
        }
    }

    @Nested
    inner class `When object is not null` {

        @Test
        fun `isNotNullAndNotEmpty returns true`() {
            "".isNotNull().`should be true`()
        }
    }
}