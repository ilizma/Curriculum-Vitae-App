package com.ilizma.education.domain.mapper

import com.ilizma.curriculum.domain.model.CurriculumVitae
import com.ilizma.education.domain.model.ComplementaryEducation

class ComplementaryEducationMapper {

    fun from(
        state: CurriculumVitae
    ): List<ComplementaryEducation> = state.complementaryEducation.map {
        ComplementaryEducation(
            type = it.type,
            title = it.title,
            hours = it.hours,
            date = it.date,
            place = it.place
        )
    }
}