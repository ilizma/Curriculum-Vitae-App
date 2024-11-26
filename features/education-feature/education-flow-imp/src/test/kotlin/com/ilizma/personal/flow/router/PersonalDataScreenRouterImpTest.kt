package com.ilizma.personal.flow.router

import androidx.activity.OnBackPressedDispatcher
import androidx.lifecycle.LifecycleOwner
import com.ilizma.personal.flow.navigator.ChartNavigator
import com.ilizma.personal.flow.navigator.PersonalDataScreenBackCloseNavigator
import com.ilizma.personal.presentation.viewmodel.PersonalDataScreenViewModel
import com.ilizma.personal.view.router.PersonalDataScreenRouter
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class PersonalDataScreenRouterImpTest {

    @RelaxedMockK
    private lateinit var lifecycleOwner: LifecycleOwner

    @RelaxedMockK
    private lateinit var onBackPressedDispatcher: OnBackPressedDispatcher

    @RelaxedMockK
    private lateinit var viewModel: PersonalDataScreenViewModel

    @RelaxedMockK
    private lateinit var viewModelLazy: Lazy<PersonalDataScreenViewModel>

    @RelaxedMockK
    private lateinit var backCloseNavigator: PersonalDataScreenBackCloseNavigator

    @RelaxedMockK
    private lateinit var chartNavigator: ChartNavigator

    private lateinit var router: PersonalDataScreenRouter

    init {
        MockKAnnotations.init(this)
        every { viewModelLazy.value } returns viewModel
    }

    @BeforeEach
    private fun setup() {
        router = PersonalDataScreenRouterImp(
            lifecycleOwner = { lifecycleOwner },
            onBackPressedDispatcher = onBackPressedDispatcher,
            viewModelLazy = viewModelLazy,
            backCloseNavigator = backCloseNavigator,
            chartNavigator = chartNavigator,
        )
    }

    @Nested
    inner class Init {

        @Test
        fun `when router is initialized, then viewModel functions are called`() {
            // when
            router.init()

            // then
            verify { viewModel.navigationAction.observe(any(), any()) }
        }

    }

}