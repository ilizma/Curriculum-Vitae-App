package com.ilizma.education.domain.mapper

import com.ilizma.education.domain.model.Education
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import com.ilizma.curriculum.domain.model.Education as CVEducation

class EducationMapperTest {

    private lateinit var mapper: EducationMapper

    @BeforeTest
    fun setup() {
        mapper = EducationMapper()
    }

    @Test
    fun `given CVEducation, when from, then result should be the expected Education`() {
        // given
        val data = CVEducation(
            title = "title",
            place = "place",
            startDate = "start_date",
            endDate = "end_date",
            currently = false,
        ).let { listOf(it) }
        val expected = Education(
            title = "title",
            place = "place",
            startDate = "start_date",
            endDate = "end_date",
            currently = false,
        ).let { listOf(it) }

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

}