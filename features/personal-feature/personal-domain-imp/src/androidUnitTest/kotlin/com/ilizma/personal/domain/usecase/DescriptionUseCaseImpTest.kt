package com.ilizma.personal.domain.usecase

import com.ilizma.curriculum.domain.model.CurriculumVitae
import com.ilizma.curriculum.domain.repository.CurriculumVitaeRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class DescriptionUseCaseImpTest {

    @RelaxedMockK
    private lateinit var repository: CurriculumVitaeRepository

    @RelaxedMockK
    private lateinit var curriculumVitae: CurriculumVitae

    private val description = "description"

    private lateinit var useCase: DescriptionUseCase

    init {
        MockKAnnotations.init(this)

        every { curriculumVitae.description } returns description
        coEvery { repository.get() } returns curriculumVitae
    }

    @BeforeTest
    fun setup() {
        useCase = DescriptionUseCaseImp(
            repository = repository,
        )
    }

    @Test
    fun `given curriculumVitae, when invoked, then result should be the expected description`() =
        runTest {
            // given
            val expected = description

            // when
            val result = useCase()

            // then
            assertEquals(expected, result)
        }

}