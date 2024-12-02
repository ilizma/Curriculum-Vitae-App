package com.ilizma.curriculum.data.cache

import com.ilizma.curriculum.data.model.CurriculumVitae
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class CurriculumVitaeCacheImpTest {

    @RelaxedMockK
    private lateinit var curriculumVitae: CurriculumVitae

    private lateinit var cache: CurriculumVitaeCache

    init {
        MockKAnnotations.init(this)
    }

    @BeforeTest
    fun setup() {
        cache = CurriculumVitaeCacheImp()
    }

    @Test
    fun `given null CurriculumVitae, when cache is called, then result should be the expected`() {
        // given
        val expected = null
        cache.cache = expected

        // when
        val result = cache.cache

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given a CurriculumVitae, when cache is called, then result should be the expected`() {
        // given
        val expected = curriculumVitae
        cache.cache = expected

        // when
        val result = cache.cache

        // then
        assertEquals(expected, result)
    }

}