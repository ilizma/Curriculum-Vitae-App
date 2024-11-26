package com.ilizma.personal.flow.navigator

import androidx.navigation.NavController
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class ChartScreenBackCloseNavigatorImpTest {

    @RelaxedMockK
    private lateinit var navController: NavController

    private lateinit var navigator: ChartScreenBackCloseNavigator

    init {
        MockKAnnotations.init(this)
    }

    @BeforeEach
    fun setup() {
        navigator = ChartScreenBackCloseNavigatorImp(
            navController = { navController },
        )
    }

    @Nested
    inner class Navigate {

        @Test
        fun `when close is called, then popBackStack is executed`() {
            // when
            navigator.close()

            // then
            verify { navController.popBackStack() }
        }

    }

}