package com.ilizma.education.domain.mapper

import com.ilizma.education.domain.model.Education
import com.ilizma.curriculum.domain.model.Education as CVEducation

class EducationMapper {

    fun from(
        list: List<CVEducation>,
    ): List<Education> = list.map {
        Education(
            title = it.title,
            place = it.place,
            startDate = it.startDate,
            endDate = it.endDate,
            currently = it.currently,
        )
    }
}