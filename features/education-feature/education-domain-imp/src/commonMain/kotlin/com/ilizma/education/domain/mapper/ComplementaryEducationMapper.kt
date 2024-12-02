package com.ilizma.education.domain.mapper

import com.ilizma.education.domain.model.ComplementaryEducation
import com.ilizma.curriculum.domain.model.ComplementaryEducation as CVComplementaryEducation

class ComplementaryEducationMapper {

    fun from(
        list: List<CVComplementaryEducation>
    ): List<ComplementaryEducation> = list.map {
        ComplementaryEducation(
            type = it.type,
            title = it.title,
            hours = it.hours,
            date = it.date,
            place = it.place
        )
    }
}