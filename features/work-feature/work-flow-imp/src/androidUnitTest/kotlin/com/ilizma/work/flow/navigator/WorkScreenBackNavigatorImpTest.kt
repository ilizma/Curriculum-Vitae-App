package com.ilizma.work.flow.navigator

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import com.ilizma.personal.flow.model.PersonalTab
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlin.test.BeforeTest
import kotlin.test.Test

class WorkScreenBackNavigatorImpTest {

    @RelaxedMockK
    private lateinit var navController: NavHostController

    private lateinit var navigator: WorkScreenBackNavigator

    init {
        MockKAnnotations.init(this)

        every { navController.currentBackStackEntry?.lifecycle?.currentState } returns Lifecycle.State.RESUMED
    }

    @BeforeTest
    fun setup() {
        navigator = WorkScreenBackNavigatorImp()
    }

    @Test
    fun `when back, then popBackStack should be executed`() {
        // when
        navigator.back(navController)

        // then
        verify { navController.popBackStack(route = PersonalTab, inclusive = false) }
    }

}