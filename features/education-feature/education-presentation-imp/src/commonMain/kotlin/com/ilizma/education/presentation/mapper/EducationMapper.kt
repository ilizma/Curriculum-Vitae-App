package com.ilizma.education.presentation.mapper

import com.ilizma.education.domain.model.ComplementaryEducation
import com.ilizma.education.domain.model.Education
import com.ilizma.education.presentation.model.EducationState
import kotlinx.collections.immutable.toImmutableList
import com.ilizma.education.presentation.model.ComplementaryEducation as PresentationComplementaryEducation
import com.ilizma.education.presentation.model.Education as PresentationEducation

class EducationMapper {

    fun from(
        educationList: List<Education>,
        complementaryEducationList: List<ComplementaryEducation>,
    ): EducationState.Success = (educationList.map {
        PresentationEducation(
            title = it.title,
            place = it.place,
            startDate = it.startDate,
            endDate = it.endDate,
            currently = it.currently,
        )
    }.toImmutableList() to complementaryEducationList.map {
        PresentationComplementaryEducation(
            type = it.type,
            title = it.title,
            hours = it.hours,
            date = it.date,
            place = it.place,
        )
    }.toImmutableList())
        .let { (education, complementaryEducation) ->
            EducationState.Success(
                education = education,
                complementaryEducation = complementaryEducation,
            )
        }
}