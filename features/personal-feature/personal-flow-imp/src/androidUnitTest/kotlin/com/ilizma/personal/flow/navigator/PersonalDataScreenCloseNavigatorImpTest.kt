package com.ilizma.personal.flow.navigator

import android.app.Activity
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlin.test.BeforeTest
import kotlin.test.Test

class PersonalDataScreenCloseNavigatorImpTest {

    @RelaxedMockK
    private lateinit var activity: Activity

    private lateinit var navigator: PersonalDataScreenCloseNavigator

    init {
        MockKAnnotations.init(this)
    }

    @BeforeTest
    fun setup() {
        navigator = PersonalDataScreenCloseNavigatorImp(
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