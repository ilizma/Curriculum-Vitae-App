package com.ilizma.curriculum.data.maper

import com.ilizma.curriculum.data.mapper.EducationMapper
import com.ilizma.curriculum.data.model.EducationDTO
import com.ilizma.curriculum.domain.model.Education
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import com.ilizma.curriculum.data.model.Education as DataEducation

class EducationMapperTest {

    private lateinit var mapper: EducationMapper

    @BeforeTest
    fun setup() {
        mapper = EducationMapper()
    }

    @Test
    fun `given EducationDTO, when from, then result should be the expected DataEducation`() {
        // given
        val data = EducationDTO(
            title = "title",
            place = "place",
            start_date = "start_date",
            end_date = "end_date",
            currently = false,
        )
        val expected = DataEducation(
            title = "title",
            place = "place",
            startDate = "start_date",
            endDate = "end_date",
            currently = false,
        )

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given DataEducation, when from, then result should be the expected Education`() {
        // given
        val data = DataEducation(
            title = "title",
            place = "place",
            startDate = "start_date",
            endDate = "end_date",
            currently = false,
        )
        val expected = Education(
            title = "title",
            place = "place",
            startDate = "start_date",
            endDate = "end_date",
            currently = false,
        )

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

}