package com.ilizma.education.domain.usecase

import com.ilizma.curriculum.domain.model.CurriculumVitae
import com.ilizma.curriculum.domain.repository.CurriculumVitaeRepository
import com.ilizma.education.domain.mapper.ComplementaryEducationMapper
import com.ilizma.education.domain.model.ComplementaryEducation
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import com.ilizma.curriculum.domain.model.ComplementaryEducation as CVComplementaryEducation

class ComplementaryEducationUseCaseImpTest {

    @RelaxedMockK
    private lateinit var repository: CurriculumVitaeRepository

    @RelaxedMockK
    private lateinit var mapper: ComplementaryEducationMapper

    @RelaxedMockK
    private lateinit var curriculumVitae: CurriculumVitae

    @RelaxedMockK
    private lateinit var complementaryEducation: ComplementaryEducation

    private lateinit var useCase: ComplementaryEducationUseCase

    init {
        MockKAnnotations.init(this)

        every { curriculumVitae.complementaryEducation } returns listOf(mockk<CVComplementaryEducation>())
        coEvery { repository.get() } returns curriculumVitae
        every { mapper.from(curriculumVitae.complementaryEducation) } returns listOf(complementaryEducation)
    }

    @BeforeTest
    fun setup() {
        useCase = ComplementaryEducationUseCaseImp(
            repository = repository,
            mapper = mapper,
        )
    }

    @Test
    fun `given curriculumVitae, when invoked, then result should be the expected complementaryEducationList`() =
        runTest {
            // given
            val expected = listOf(complementaryEducation)

            // when
            val result = useCase()

            // then
            assertEquals(expected, result)
        }

}