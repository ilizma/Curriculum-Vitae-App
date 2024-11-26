package com.ilizma.personal.flow.navigator

import android.app.Activity
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class PersonalDataScreenBackCloseNavigatorImpTest {

    @RelaxedMockK
    private lateinit var activity: Activity

    private lateinit var navigator: PersonalDataScreenBackCloseNavigator

    init {
        MockKAnnotations.init(this)
    }

    @BeforeEach
    fun setup() {
        navigator = PersonalDataScreenBackCloseNavigatorImp(
            activity = activity,
        )
    }

    @Nested
    inner class Navigate {

        @Test
        fun `when close is called, then finish is executed`() {
            // when
            navigator.close()

            // then
            verify { activity.finish() }
        }

    }

}