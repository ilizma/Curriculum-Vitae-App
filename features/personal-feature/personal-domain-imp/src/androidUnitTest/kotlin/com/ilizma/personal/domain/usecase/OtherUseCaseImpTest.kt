package com.ilizma.personal.domain.usecase

import com.ilizma.curriculum.domain.model.CurriculumVitae
import com.ilizma.curriculum.domain.repository.CurriculumVitaeRepository
import com.ilizma.personal.domain.mapper.OtherMapper
import com.ilizma.personal.domain.model.Other
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import com.ilizma.curriculum.domain.model.Other as CVOther

class OtherUseCaseImpTest {

    @RelaxedMockK
    private lateinit var repository: CurriculumVitaeRepository

    @RelaxedMockK
    private lateinit var mapper: OtherMapper

    @RelaxedMockK
    private lateinit var curriculumVitae: CurriculumVitae

    @RelaxedMockK
    private lateinit var cvOther: CVOther

    @RelaxedMockK
    private lateinit var other: Other

    private lateinit var useCase: OtherUseCase

    init {
        MockKAnnotations.init(this)

        every { curriculumVitae.other } returns listOf(cvOther)
        coEvery { repository.get() } returns curriculumVitae
        every { mapper.from(listOf(cvOther)) } returns listOf(other)
    }

    @BeforeTest
    fun setup() {
        useCase = OtherUseCaseImp(
            repository = repository,
            mapper = mapper,
        )
    }

    @Test
    fun `given curriculumVitae, when invoked, then result should be the expected otherList`() =
        runTest {
            // given
            val expected = listOf(other)

            // when
            val result = useCase()

            // then
            assertEquals(expected, result)
        }

}