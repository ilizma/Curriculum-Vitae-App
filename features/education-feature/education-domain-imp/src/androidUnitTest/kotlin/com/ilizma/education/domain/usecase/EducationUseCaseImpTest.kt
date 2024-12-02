package com.ilizma.education.domain.usecase

import com.ilizma.curriculum.domain.model.CurriculumVitae
import com.ilizma.curriculum.domain.repository.CurriculumVitaeRepository
import com.ilizma.education.domain.mapper.EducationMapper
import com.ilizma.education.domain.model.Education
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import com.ilizma.curriculum.domain.model.Education as CVEducation

class EducationUseCaseImpTest {

    @RelaxedMockK
    private lateinit var repository: CurriculumVitaeRepository

    @RelaxedMockK
    private lateinit var mapper: EducationMapper

    @RelaxedMockK
    private lateinit var curriculumVitae: CurriculumVitae

    @RelaxedMockK
    private lateinit var education: Education

    private lateinit var useCase: EducationUseCase

    init {
        MockKAnnotations.init(this)

        every { curriculumVitae.education } returns listOf(mockk<CVEducation>())
        coEvery { repository.get() } returns curriculumVitae
        every { mapper.from(curriculumVitae.education) } returns listOf(education)
    }

    @BeforeTest
    fun setup() {
        useCase = EducationUseCaseImp(
            repository = repository,
            mapper = mapper,
        )
    }

    @Test
    fun `given curriculumVitae, when invoked, then result should be the expected educationList`() =
        runTest {
            // given
            val expected = listOf(education)

            // when
            val result = useCase()

            // then
            assertEquals(expected, result)
        }

}