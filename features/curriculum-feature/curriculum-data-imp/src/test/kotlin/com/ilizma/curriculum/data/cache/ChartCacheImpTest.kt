package com.ilizma.curriculum.data.cache

import com.ilizma.curriculum.data.model.ChartState
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class ChartCacheImpTest {

    private lateinit var cache: ChartCache

    @BeforeEach
    private fun setup() {
        cache = ChartCacheImp()
    }

    @Nested
    inner class GetCache {

        @Test
        fun `given null ChartState, when cache is called, then result should be the expected`() {
            // given
            val expected = null
            cache.cache = expected

            // when
            val result = cache.cache

            // then
            assertEquals(expected, result)
        }

        @Test
        fun `given a ChartState, when cache is called, then result should be the expected`() {
            // given
            val expected = mockk<ChartState.Success>()
            cache.cache = expected

            // when
            val result = cache.cache

            // then
            assertEquals(expected, result)
        }

    }

}