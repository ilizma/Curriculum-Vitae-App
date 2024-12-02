package com.ilizma.personal.flow.navigator

import android.content.Context
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.BeforeTest

class PhoneNavigatorImpTest {

    @RelaxedMockK
    private lateinit var context: Context

    private lateinit var navigator: PhoneNavigator

    init {
        MockKAnnotations.init(this)
    }

    @BeforeTest
    fun setup() {
        navigator = PhoneNavigatorImp(
            context = context
        )
    }

    @Test
    fun `given phone, when navigate, then startActivity`() {
        // given
        val phone = "phone"

        // when
        navigator.navigate(phone)

        // then
        verify { context.startActivity(any()) }
    }

}