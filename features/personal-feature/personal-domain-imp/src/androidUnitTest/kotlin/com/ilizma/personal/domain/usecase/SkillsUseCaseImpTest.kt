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

class SkillsUseCaseImpTest {

    @RelaxedMockK
    private lateinit var repository: CurriculumVitaeRepository

    @RelaxedMockK
    private lateinit var curriculumVitae: CurriculumVitae

    private val skill = "skill"

    private lateinit var useCase: SkillsUseCase

    init {
        MockKAnnotations.init(this)

        every { curriculumVitae.skills } returns listOf(skill)
        coEvery { repository.get() } returns curriculumVitae
    }

    @BeforeTest
    fun setup() {
        useCase = SkillsUseCaseImp(
            repository = repository,
        )
    }

    @Test
    fun `given curriculumVitae, when invoked, then result should be the expected skills`() =
        runTest {
            // given
            val expected = listOf(skill)

            // when
            val result = useCase()

            // then
            assertEquals(expected, result)
        }

}