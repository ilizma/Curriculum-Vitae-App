package com.ilizma.education.flow.navigator

import kotlin.test.Test
import kotlin.test.BeforeTest

class EducationScreenBackCloseNavigatorImpTest {

    private lateinit var navigator: EducationScreenBackCloseNavigator

    @BeforeTest
    fun setup() {
        navigator = EducationScreenBackCloseNavigatorImp()
    }

    @Test
    fun `when , then `() {
        // given

        // when
        navigator.close()

        // then
    }

}