package com.ilizma.personal.flow.navigator

import kotlin.test.Test
import kotlin.test.BeforeTest

class PhoneNavigatorImpTest {

    private lateinit var navigator: PhoneNavigator

    @BeforeTest
    fun setup() {
        navigator = PhoneNavigatorImp()
    }

    @Test
    fun `given phone, when navigate, then `() {
        // given
        val phone = "phone"

        // when
        navigator.navigate(phone)

        // then
    }

}