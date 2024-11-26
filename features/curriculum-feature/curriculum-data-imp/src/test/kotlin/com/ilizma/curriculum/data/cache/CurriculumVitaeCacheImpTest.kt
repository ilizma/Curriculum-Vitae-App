package com.ilizma.curriculum.data.cache

import com.ilizma.curriculum.data.model.DashboardState
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class CurriculumVitaeCacheImpTest {

    private lateinit var cache: CurriculumVitaeCache

    @BeforeEach
    private fun setup() {
        cache = CurriculumVitaeCacheImp()
    }

    @Nested
    inner class GetCache {

        @Test
        fun `given null DashboardState, when cache is called, then result should be the expected`() {
            // given
            val expected = null
            cache.cache = expected

            // when
            val result = cache.cache

            // then
            assertEquals(expected, result)
        }

        @Test
        fun `given a DashboardState, when cache is called, then result should be the expected`() {
            // given
            val expected = mockk<DashboardState.Success>()
            cache.cache = expected

            // when
            val result = cache.cache

            // then
            assertEquals(expected, result)
        }

    }

}