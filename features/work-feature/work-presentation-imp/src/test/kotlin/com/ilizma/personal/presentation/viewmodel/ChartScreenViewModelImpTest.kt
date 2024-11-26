package com.ilizma.curriculum.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ilizma.curriculum.domain.model.ChartState
import com.ilizma.curriculum.domain.usecase.ChartUseCase
import com.ilizma.curriculum.presentation.mapper.ChartStateMapper
import com.ilizma.curriculum.presentation.model.ChartScreenNavigationAction.Back
import com.ilizma.presentation.SingleLiveEvent
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
import com.ilizma.curriculum.presentation.model.ChartState as PresentationChartState

@ExtendWith(InstantExecutorExtension::class)
internal class ChartScreenViewModelImpTest {

    @RelaxedMockK
    private lateinit var useCase: ChartUseCase

    @RelaxedMockK
    private lateinit var mapper: ChartStateMapper

    @RelaxedMockK
    private lateinit var backgroundScheduler: Scheduler

    @RelaxedMockK
    private lateinit var compositeDisposable: CompositeDisposable

    private lateinit var viewModel: ChartScreenViewModel

    init {
        MockKAnnotations.init(this)
    }

    @BeforeEach
    private fun setup() {
        viewModel = ChartScreenViewModelImp(
            useCase = useCase,
            mapper = mapper,
            backgroundScheduler = backgroundScheduler,
            compositeDisposable = compositeDisposable,
            _chartState = MutableLiveData(),
            _error = MutableLiveData(),
            _navigationAction = SingleLiveEvent(),
        )
    }

    @Nested
    inner class GetChart {

        @Test
        fun `given a Success ChartState, when getChart is executed, then the liveData value should be the expected`() {
            // given
            val state = mockk<ChartState.Success>()
            val presentationState = mockk<PresentationChartState.Success>()
            every { useCase() } returns Single.just(state)
            every { mapper.from(state) } returns presentationState

            // when
            viewModel.getChart()

            // then
            verify { useCase() }
        }

        @Test
        fun `given a Error ChartState, when getChart is executed, then the liveData value should be the expected`() {
            // given
            val state = mockk<ChartState.Error>()
            every { useCase() } returns Single.just(state)

            // when
            viewModel.getChart()

            // then
            verify { useCase() }
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