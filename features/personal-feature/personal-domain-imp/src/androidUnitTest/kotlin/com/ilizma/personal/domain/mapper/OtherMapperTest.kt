package com.ilizma.personal.domain.mapper

import com.ilizma.personal.domain.model.Other
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import com.ilizma.curriculum.domain.model.Other as CVOther

class OtherMapperTest {

    private lateinit var mapper: OtherMapper

    @BeforeTest
    fun setup() {
        mapper = OtherMapper()
    }

    @Test
    fun `given CVOther, when from, then result should be the expected Other`() {
        // given
        val data = CVOther(
            title = "title",
            description = "description",
            link = "link",
        ).let { listOf(it) }
        val expected = Other(
            title = "title",
            description = "description",
            link = "link",
        ).let { listOf(it) }

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

}