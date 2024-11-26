package com.ilizma.personal.flow.navigator

import androidx.navigation.NavController
import com.ilizma.curriculum.view.fragment.DashboardScreenFragmentDirections
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class ChartNavigatorImpTest {

    @RelaxedMockK
    private lateinit var navController: NavController

    private lateinit var navigator: ChartNavigator

    init {
        MockKAnnotations.init(this)
    }

    @BeforeEach
    fun setup() {
        navigator = ChartNavigatorImp(
            navController = { navController },
        )
    }

    @Nested
    inner class Navigate {

        @Test
        fun `when navigate is called, then navigate is executed`() {
            // when
            navigator.navigate()

            // then
            verify { navController.navigate(DashboardScreenFragmentDirections.goFromDashboardToChart()) }
        }

    }

}