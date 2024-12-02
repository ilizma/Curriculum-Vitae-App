package com.ilizma.work.flow.router

import com.ilizma.work.flow.navigator.WorkScreenBackCloseNavigator
import com.ilizma.work.presentation.viewmodel.WorkScreenViewModel
import com.ilizma.work.view.router.WorkScreenRouter
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class WorkScreenRouterImpTest {

    @RelaxedMockK
    private lateinit var viewModel: WorkScreenViewModel

    @RelaxedMockK
    private lateinit var backCloseNavigator: WorkScreenBackCloseNavigator

    private lateinit var router: WorkScreenRouter


    init {
        MockKAnnotations.init(this)
    }

    private fun setup(standardTestDispatcher: TestDispatcher) {
        router = WorkScreenRouterImp(
            dispatcher = standardTestDispatcher,
            backCloseNavigator = backCloseNavigator,
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when router is initialized, then viewModel functions are called`() = runTest {
        // given
        val standardTestDispatcher = StandardTestDispatcher(testScheduler)
        setup(standardTestDispatcher)

        // when
        router.init(
            coroutineScope = CoroutineScope(standardTestDispatcher),
            viewModel = viewModel,
        )
        advanceUntilIdle()

        // then
        coVerify { viewModel.navigationAction.collect(any()) }
    }

}