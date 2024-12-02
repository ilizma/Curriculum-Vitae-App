package com.ilizma.work.presentation.viewmodel

import com.ilizma.work.domain.model.Work
import com.ilizma.work.domain.usecase.WorkUseCase
import com.ilizma.work.presentation.mapper.WorkMapper
import com.ilizma.work.presentation.model.WorkIntent
import com.ilizma.work.presentation.model.WorkScreenNavigationAction
import com.ilizma.work.presentation.model.WorkState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class WorkScreenViewModelImpTest {

    @RelaxedMockK
    private lateinit var workUseCase: WorkUseCase

    @RelaxedMockK
    private lateinit var mapper: WorkMapper

    @RelaxedMockK
    private lateinit var work: Work

    @RelaxedMockK
    private lateinit var successWorkState: WorkState.Success

    private lateinit var viewModel: WorkScreenViewModel

    init {
        MockKAnnotations.init(this)

        coEvery { workUseCase.invoke() } returns listOf(work)
        every {
            mapper.from(
                data = listOf(work),
            )
        } returns successWorkState
    }

    private fun setup(standardTestDispatcher: TestDispatcher) {
        viewModel = WorkScreenViewModelImp(
            dispatcher = standardTestDispatcher,
            useCase = workUseCase,
            mapper = mapper,
            isDebug = true,
            _workState = MutableStateFlow(WorkState.Loading),
            _navigationAction = MutableSharedFlow(),
        )
    }

    @Test
    fun `when init, then result should be the expected successWorkState`() =
        runTest {
            // given
            val standardTestDispatcher = StandardTestDispatcher(testScheduler)
            val expected = successWorkState

            // when
            setup(standardTestDispatcher)

            backgroundScope.launch(standardTestDispatcher) {
                // then
                assertEquals(expected, viewModel.workState.last())
            }

        }

    @Test
    fun `given Retry WorkIntent, when onIntent, then result should be the expected successWorkState`() =
        runTest {
            // given
            val standardTestDispatcher = StandardTestDispatcher(testScheduler)
            setup(standardTestDispatcher)
            val expected = successWorkState

            backgroundScope.launch(standardTestDispatcher) {
                // when
                viewModel.onIntent(WorkIntent.Retry)

                // then
                assertEquals(expected, viewModel.workState.last())
            }

        }

    @Test
    fun `given Back WorkIntent, when onIntent, then result should be the expected Back`() =
        runTest {
            // given
            val standardTestDispatcher = StandardTestDispatcher(testScheduler)
            setup(standardTestDispatcher)
            val expected = WorkScreenNavigationAction.Back

            // when
            viewModel.onIntent(WorkIntent.Back)

            // then
            assertEquals(expected, viewModel.navigationAction.first())
        }
    
}