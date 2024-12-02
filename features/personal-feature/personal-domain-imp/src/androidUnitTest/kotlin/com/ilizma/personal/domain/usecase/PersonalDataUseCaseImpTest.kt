package com.ilizma.personal.domain.usecase

import com.ilizma.curriculum.domain.model.CurriculumVitae
import com.ilizma.curriculum.domain.repository.CurriculumVitaeRepository
import com.ilizma.personal.domain.mapper.PersonalDataMapper
import com.ilizma.personal.domain.model.PersonalData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import com.ilizma.curriculum.domain.model.PersonalData as CVPersonalData

class PersonalDataUseCaseImpTest {

    @RelaxedMockK
    private lateinit var repository: CurriculumVitaeRepository

    @RelaxedMockK
    private lateinit var mapper: PersonalDataMapper

    @RelaxedMockK
    private lateinit var curriculumVitae: CurriculumVitae

    @RelaxedMockK
    private lateinit var cvPersonalData: CVPersonalData

    @RelaxedMockK
    private lateinit var personalData: PersonalData

    private lateinit var useCase: PersonalDataUseCase

    init {
        MockKAnnotations.init(this)

        every { curriculumVitae.personalData } returns cvPersonalData
        coEvery { repository.get() } returns curriculumVitae
        every { mapper.from(cvPersonalData) } returns personalData
    }

    @BeforeTest
    fun setup() {
        useCase = PersonalDataUseCaseImp(
            repository = repository,
            mapper = mapper,
        )
    }

    @Test
    fun `given curriculumVitae, when invoked, then result should be the expected personalData`() =
        runTest {
            // given
            val expected = personalData

            // when
            val result = useCase()

            // then
            assertEquals(expected, result)
        }

}