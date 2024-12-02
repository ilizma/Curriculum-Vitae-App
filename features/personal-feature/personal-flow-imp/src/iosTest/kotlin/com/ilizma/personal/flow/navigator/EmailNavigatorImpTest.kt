package com.ilizma.personal.flow.navigator

import kotlin.test.Test
import kotlin.test.BeforeTest

class EmailNavigatorImpTest {

    private lateinit var navigator: EmailNavigator

    @BeforeTest
    fun setup() {
        navigator = EmailNavigatorImp()
    }

    @Test
    fun `given email, when navigate, then`() {
        // given
        val email = "email"

        // when
        navigator.navigate(email)

        // then
    }

}