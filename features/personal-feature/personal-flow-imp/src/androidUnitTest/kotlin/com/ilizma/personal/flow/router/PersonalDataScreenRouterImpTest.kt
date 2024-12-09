package com.ilizma.personal.flow.router

import com.ilizma.personal.flow.navigator.EmailNavigator
import com.ilizma.personal.flow.navigator.PersonalDataScreenCloseNavigator
import com.ilizma.personal.flow.navigator.PhoneNavigator
import com.ilizma.personal.presentation.viewmodel.PersonalDataScreenViewModel
import com.ilizma.personal.view.router.PersonalDataScreenRouter
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

class PersonalDataScreenRouterImpTest {

    @RelaxedMockK
    private lateinit var viewModel: PersonalDataScreenViewModel

    @RelaxedMockK
    private lateinit var phoneNavigator: PhoneNavigator

    @RelaxedMockK
    private lateinit var emailNavigator: EmailNavigator

    @RelaxedMockK
    private lateinit var closeNavigator: PersonalDataScreenCloseNavigator

    private lateinit var router: PersonalDataScreenRouter


    init {
        MockKAnnotations.init(this)
    }

    private fun setup(standardTestDispatcher: TestDispatcher) {
        router = PersonalDataScreenRouterImp(
            dispatcher = standardTestDispatcher,
            phoneNavigator = phoneNavigator,
            emailNavigator = emailNavigator,
            closeNavigator = closeNavigator,
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