package com.ilizma.curriculum.data.maper

import com.ilizma.curriculum.data.mapper.OtherMapper
import com.ilizma.curriculum.data.model.OtherDTO
import com.ilizma.curriculum.domain.model.Other
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import com.ilizma.curriculum.data.model.Other as DataOther

class OtherMapperTest {

    private lateinit var mapper: OtherMapper

    @BeforeTest
    fun setup() {
        mapper = OtherMapper()
    }

    @Test
    fun `given OtherDTO, when from, then result should be the expected DataOther`() {
        // given
        val data = OtherDTO(
            title = "title",
            description = "description",
            link = "link",
        )
        val expected = DataOther(
            title = "title",
            description = "description",
            link = "link",
        )

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given DataOther, when from, then result should be the expected Other`() {
        // given
        val data = DataOther(
            title = "title",
            description = "description",
            link = "link",
        )
        val expected = Other(
            title = "title",
            description = "description",
            link = "link",
        )

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

}