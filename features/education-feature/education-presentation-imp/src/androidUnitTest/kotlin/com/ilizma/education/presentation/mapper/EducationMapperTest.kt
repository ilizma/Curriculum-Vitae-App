package com.ilizma.education.presentation.mapper

import com.ilizma.education.domain.model.ComplementaryEducation
import com.ilizma.education.domain.model.Education
import com.ilizma.education.presentation.model.EducationState
import kotlinx.collections.immutable.persistentListOf
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import com.ilizma.education.presentation.model.ComplementaryEducation as PresentationComplementaryEducation
import com.ilizma.education.presentation.model.Education as PresentationEducation

class EducationMapperTest {

    private lateinit var mapper: EducationMapper

    @BeforeTest
    fun setup() {
        mapper = EducationMapper()
    }

    @Test
    fun `given educationList and complementaryEducationList, when from, then result should be the expected Success EducationState`() {
        // given
        val educationList = Education(
            title = "title",
            place = "place",
            startDate = "start_date",
            endDate = "end_date",
            currently = false,
        ).let { listOf(it) }
        val complementaryEducationList = ComplementaryEducation(
            type = "type",
            title = "title",
            hours = "hours",
            date = "date",
            place = "place",
        ).let { listOf(it) }
        val expected = EducationState.Success(
            education = PresentationEducation(
                title = "title",
                place = "place",
                startDate = "start_date",
                endDate = "end_date",
                currently = false,
            ).let { persistentListOf(it) },
            complementaryEducation = PresentationComplementaryEducation(
                type = "type",
                title = "title",
                hours = "hours",
                date = "date",
                place = "place",
            ).let { persistentListOf(it) }
        )

        // when
        val result = mapper.from(
            educationList = educationList,
            complementaryEducationList = complementaryEducationList,
        )

        // then
        assertEquals(expected, result)
    }

}