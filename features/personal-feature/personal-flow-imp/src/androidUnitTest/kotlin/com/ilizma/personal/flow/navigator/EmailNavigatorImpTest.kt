package com.ilizma.personal.flow.navigator

import android.content.Context
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.BeforeTest

class EmailNavigatorImpTest {

    @RelaxedMockK
    private lateinit var context: Context

    private lateinit var navigator: EmailNavigator

    init {
        MockKAnnotations.init(this)
    }

    @BeforeTest
    fun setup() {
        navigator = EmailNavigatorImp(
            context = context
        )
    }

    @Test
    fun `given email, when navigate, then startActivity`() {
        // given
        val email = "email"

        // when
        navigator.navigate(email)

        // then
        verify { context.startActivity(any()) }
    }

}