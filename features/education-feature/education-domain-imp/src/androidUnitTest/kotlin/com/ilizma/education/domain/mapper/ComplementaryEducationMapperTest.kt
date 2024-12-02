package com.ilizma.education.domain.mapper

import com.ilizma.education.domain.model.ComplementaryEducation
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import com.ilizma.curriculum.domain.model.ComplementaryEducation as CVComplementaryEducation

class ComplementaryEducationMapperTest {

    private lateinit var mapper: ComplementaryEducationMapper

    @BeforeTest
    fun setup() {
        mapper = ComplementaryEducationMapper()
    }

    @Test
    fun `given CVComplementaryEducation, when from, then result should be the expected ComplementaryEducation`() {
        // given
        val data = CVComplementaryEducation(
            type = "type",
            title = "title",
            hours = "hours",
            date = "date",
            place = "place",
        ).let { listOf(it) }
        val expected = ComplementaryEducation(
            type = "type",
            title = "title",
            hours = "hours",
            date = "date",
            place = "place",
        ).let { listOf(it) }

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

}