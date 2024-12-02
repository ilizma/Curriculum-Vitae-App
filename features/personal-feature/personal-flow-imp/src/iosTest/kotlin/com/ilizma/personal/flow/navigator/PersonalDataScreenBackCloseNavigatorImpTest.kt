package com.ilizma.personal.flow.navigator

import kotlin.test.Test
import kotlin.test.BeforeTest

class PersonalDataScreenBackCloseNavigatorImpTest {

    private lateinit var navigator: PersonalDataScreenBackCloseNavigator

    @BeforeTest
    fun setup() {
        navigator = PersonalDataScreenBackCloseNavigatorImp()
    }

    @Test
    fun `when close, then`() {
        // when
        navigator.close()

        // then
    }

}