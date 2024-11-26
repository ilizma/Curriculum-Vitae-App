package com.ilizma.curriculum.domain.usecase

import com.ilizma.curriculum.domain.model.DashboardState
import com.ilizma.curriculum.domain.repository.CurriculumRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class PersonalDataUseCaseImpTest {

    @RelaxedMockK
    private lateinit var repository: CurriculumRepository

    private lateinit var dataSource: PersonalDataUseCase

    init {
        MockKAnnotations.init(this)
    }

    @BeforeEach
    private fun setup() {
        dataSource = PersonalDataUseCaseImp(
            repository,
        )
    }

    @Nested
    inner class Invoke {

        @Test
        fun `given expected DashboardState, when invoked, then result should be the expected`() {
            // given
            val expected = mockk<DashboardState>()
            every { repository.getDashboardState() } returns Single.just(expected)

            // when
            val resultObserver = repository.getDashboardState()
                .observeOn(Schedulers.trampoline())
                .test()

            // then
            resultObserver.assertValue { it == expected }
        }

    }

}