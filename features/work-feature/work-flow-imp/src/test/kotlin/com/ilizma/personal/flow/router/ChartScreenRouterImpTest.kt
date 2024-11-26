package com.ilizma.personal.flow.router

import androidx.activity.OnBackPressedDispatcher
import androidx.lifecycle.LifecycleOwner
import com.ilizma.personal.flow.navigator.ChartScreenBackCloseNavigator
import com.ilizma.curriculum.presentation.viewmodel.ChartScreenViewModel
import com.ilizma.curriculum.view.router.ChartScreenRouter
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class ChartScreenRouterImpTest {

    @RelaxedMockK
    private lateinit var lifecycleOwner: LifecycleOwner

    @RelaxedMockK
    private lateinit var onBackPressedDispatcher: OnBackPressedDispatcher

    @RelaxedMockK
    private lateinit var viewModel: ChartScreenViewModel

    @RelaxedMockK
    private lateinit var viewModelLazy: Lazy<ChartScreenViewModel>

    @RelaxedMockK
    private lateinit var navigator: ChartScreenBackCloseNavigator

    private lateinit var router: ChartScreenRouter

    init {
        MockKAnnotations.init(this)
        every { viewModelLazy.value } returns viewModel
    }

    @BeforeEach
    private fun setup() {
        router = ChartScreenRouterImp(
            lifecycleOwner = { lifecycleOwner },
            onBackPressedDispatcher = onBackPressedDispatcher,
            viewModelLazy = viewModelLazy,
            navigator = navigator,
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