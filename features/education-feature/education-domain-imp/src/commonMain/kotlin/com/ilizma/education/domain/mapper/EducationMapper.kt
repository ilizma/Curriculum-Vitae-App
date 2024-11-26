package com.ilizma.education.domain.mapper

import com.ilizma.curriculum.domain.model.CurriculumVitae
import com.ilizma.education.domain.model.Education

class EducationMapper {

    fun from(
        state: CurriculumVitae
    ): List<Education> = state.education.map {
        Education(
            title = it.title,
            place = it.place,
            startDate = it.startDate,
            endDate = it.endDate,
            currently = it.currently,
        )
    }
}