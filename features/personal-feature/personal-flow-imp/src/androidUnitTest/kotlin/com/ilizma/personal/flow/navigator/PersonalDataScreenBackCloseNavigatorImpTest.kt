package com.ilizma.personal.flow.navigator

import android.app.Activity
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.BeforeTest

class PersonalDataScreenBackCloseNavigatorImpTest {

    @RelaxedMockK
    private lateinit var activity: Activity

    private lateinit var navigator: PersonalDataScreenBackCloseNavigator

    init {
        MockKAnnotations.init(this)
    }

    @BeforeTest
    fun setup() {
        navigator = PersonalDataScreenBackCloseNavigatorImp(
            activity = activity
        )
    }

    @Test
    fun `when close, then finish`() {
        // when
        navigator.close()

        // then
        verify { activity.finish() }
    }

}