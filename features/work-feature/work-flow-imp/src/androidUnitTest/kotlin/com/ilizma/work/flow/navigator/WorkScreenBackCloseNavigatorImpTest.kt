package com.ilizma.work.flow.navigator

import kotlin.test.BeforeTest
import kotlin.test.Test

class WorkScreenBackCloseNavigatorImpTest {

    private lateinit var navigator: WorkScreenBackCloseNavigator

    @BeforeTest
    fun setup() {
        navigator = WorkScreenBackCloseNavigatorImp()
    }

    @Test
    fun `when close, then`() {
        // when
        navigator.close()

        // then
    }

}