package com.ilizma.personal.flow.navigator

import kotlin.test.BeforeTest
import kotlin.test.Test

class PersonalDataScreenCloseNavigatorImpTest {

    private lateinit var navigator: PersonalDataScreenCloseNavigator

    @BeforeTest
    fun setup() {
        navigator = PersonalDataScreenCloseNavigatorImp()
    }

    @Test
    fun whenCloseThen() {
        // when
        navigator.close()

        // then
    }

}