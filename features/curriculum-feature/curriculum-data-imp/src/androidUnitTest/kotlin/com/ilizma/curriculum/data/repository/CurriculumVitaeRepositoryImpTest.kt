package com.ilizma.curriculum.data.repository

import com.ilizma.curriculum.data.cache.CurriculumVitaeCache
import com.ilizma.curriculum.data.datasource.CurriculumVitaeDataSource
import com.ilizma.curriculum.data.mapper.CurriculumVitaeMapper
import com.ilizma.curriculum.domain.model.CurriculumVitae
import com.ilizma.curriculum.domain.repository.CurriculumVitaeRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import com.ilizma.curriculum.data.model.CurriculumVitae as DataCurriculumVitae

class CurriculumVitaeRepositoryImpTest {

    @RelaxedMockK
    private lateinit var dataCurriculumVitae: DataCurriculumVitae

    @RelaxedMockK
    private lateinit var curriculumVitae: CurriculumVitae

    @RelaxedMockK
    private lateinit var dataSource: CurriculumVitaeDataSource

    @RelaxedMockK
    private lateinit var cache: CurriculumVitaeCache

    @RelaxedMockK
    private lateinit var mapper: CurriculumVitaeMapper

    private lateinit var repository: CurriculumVitaeRepository

    init {
        MockKAnnotations.init(this)

        coEvery { dataSource.get() } returns dataCurriculumVitae
        every { cache.cache } returns dataCurriculumVitae
        every { mapper.from(dataCurriculumVitae) } returns curriculumVitae
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setup() {
        repository = CurriculumVitaeRepositoryImp(
            dataSource = dataSource,
            cache = cache,
            mapper = mapper,
        )
    }

    @Test
    fun `given not cached curriculumVitae, when get, then result should be the expected`() =
        runTest {
            // given
            every { cache.cache } returns null
            val expected = curriculumVitae

            // when
            val result = repository.get()

            // then
            coVerify { dataSource.get() }
            assertEquals(expected, result)
        }

    @Test
    fun `given a cached curriculumVitae, when get, then result should be the expected`() =
        runTest {
            // given
            val expected = curriculumVitae

            // when
            val result = repository.get()

            // then
            coVerify(exactly = 0) { dataSource.get() }
            assertEquals(expected, result)
        }

}