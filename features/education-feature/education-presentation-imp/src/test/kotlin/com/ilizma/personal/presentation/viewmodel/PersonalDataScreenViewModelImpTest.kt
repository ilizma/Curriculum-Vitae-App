package com.ilizma.curriculum.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ilizma.curriculum.domain.model.DashboardState
import com.ilizma.curriculum.domain.usecase.PersonalDataUseCase
import com.ilizma.personal.presentation.mapper.PersonalDataMapper
import com.ilizma.personal.presentation.model.PersonalDataScreenNavigationAction.Back
import com.ilizma.personal.presentation.model.PersonalDataScreenNavigationAction.Detail
import com.ilizma.personal.presentation.viewmodel.PersonalDataScreenViewModel
import com.ilizma.personal.presentation.viewmodel.PersonalDataScreenViewModelImp
import com.ilizma.test.executor.InstantExecutorExtension
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import com.ilizma.curriculum.presentation.model.DashboardState as PresentationDashboardState

@ExtendWith(InstantExecutorExtension::class)
internal class PersonalDataScreenViewModelImpTest {

    @RelaxedMockK
    private lateinit var useCase: PersonalDataUseCase

    @RelaxedMockK
    private lateinit var mapper: PersonalDataMapper

    @RelaxedMockK
    private lateinit var backgroundScheduler: Scheduler

    @RelaxedMockK
    private lateinit var compositeDisposable: CompositeDisposable

    private lateinit var viewModel: PersonalDataScreenViewModel

    init {
        MockKAnnotations.init(this)
    }

    @BeforeEach
    private fun setup() {
        viewModel = PersonalDataScreenViewModelImp(
            useCase = useCase,
            mapper = mapper,
            backgroundScheduler = backgroundScheduler,
            compositeDisposable = compositeDisposable,
            _dashboardState = MutableLiveData(),
            _error = MutableLiveData(),
            _navigationAction = SingleLiveEvent(),
        )
    }

    @Nested
    inner class GetDashboard {

        @Test
        fun `given a Success DashboardState, when getDashboard is executed, then the liveData value should be the expected`() {
            // given
            val state = mockk<DashboardState.Success>()
            val presentationState = mockk<PresentationDashboardState.Success>()
            every { useCase() } returns Single.just(state)
            every { mapper.from(state) } returns presentationState

            // when
            viewModel.getDashboard()

            // then
            verify { useCase() }
        }

        @Test
        fun `given a Error DashboardState, when getDashboard is executed, then the liveData value should be the expected`() {
            // given
            val state = mockk<DashboardState.Error>(relaxed = true)
            every { useCase() } returns Single.just(state)

            // when
            viewModel.getDashboard()

            // then
            verify { useCase() }
        }

    }

    @Nested
    inner class OpenDetail {

        @Test
        fun `given initialized viewModel, when openDetail is executed, then the liveData value should be the expected`() {
            // given
            val expected = Detail

            // when
            viewModel.openDetail()

            // then
            assertEquals(expected, viewModel.navigationAction.value)
        }

    }

    @Nested
    inner class OnBack {

        @Test
        fun `given initialized viewModel, when onBack is executed, then the liveData value should be the expected`() {
            // given
            val expected = Back

            // when
            viewModel.onBack()

            // then
            assertEquals(expected, viewModel.navigationAction.value)
        }

    }

}