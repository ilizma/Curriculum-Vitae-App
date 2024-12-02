package com.ilizma.curriculum.data.maper

import com.ilizma.curriculum.data.mapper.ComplementaryEducationMapper
import com.ilizma.curriculum.data.model.ComplementaryEducationDTO
import com.ilizma.curriculum.domain.model.ComplementaryEducation
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import com.ilizma.curriculum.data.model.ComplementaryEducation as DataComplementaryEducation

class ComplementaryEducationMapperTest {

    private lateinit var mapper: ComplementaryEducationMapper

    @BeforeTest
    fun setup() {
        mapper = ComplementaryEducationMapper()
    }

    @Test
    fun `given ComplementaryEducationDTO, when from, then result should be the expected DataComplementaryEducation`() {
        // given
        val data = ComplementaryEducationDTO(
            type = "type",
            title = "title",
            hours = "hours",
            date = "date",
            place = "place",
        )
        val expected = DataComplementaryEducation(
            type = "type",
            title = "title",
            hours = "hours",
            date = "date",
            place = "place",
        )

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given DataComplementaryEducation, when from, then result should be the expected ComplementaryEducation`() {
        // given
        val data = DataComplementaryEducation(
            type = "type",
            title = "title",
            hours = "hours",
            date = "date",
            place = "place",
        )
        val expected = ComplementaryEducation(
            type = "type",
            title = "title",
            hours = "hours",
            date = "date",
            place = "place",
        )

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

}