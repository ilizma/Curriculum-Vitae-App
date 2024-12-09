package com.ilizma.education.flow.router

import androidx.navigation.NavHostController
import com.ilizma.education.flow.navigator.EducationScreenBackNavigator
import com.ilizma.education.presentation.viewmodel.EducationScreenViewModel
import com.ilizma.education.view.router.EducationScreenRouter
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

class EducationScreenRouterImpTest {

    @RelaxedMockK
    private lateinit var viewModel: EducationScreenViewModel

    @RelaxedMockK
    private lateinit var navController: NavHostController

    @RelaxedMockK
    private lateinit var backNavigator: EducationScreenBackNavigator

    private lateinit var router: EducationScreenRouter


    init {
        MockKAnnotations.init(this)
    }

    private fun setup(standardTestDispatcher: TestDispatcher) {
        router = EducationScreenRouterImp(
            dispatcher = standardTestDispatcher,
            backNavigator = backNavigator,
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
            navController = navController,
            viewModel = viewModel,
        )
        advanceUntilIdle()

        // then
        coVerify { viewModel.navigationAction.collect(any()) }
    }

}