package com.ilizma.curriculum.data.mapper

import com.ilizma.curriculum.data.model.EducationDTO
import com.ilizma.curriculum.domain.model.Education
import com.ilizma.curriculum.data.model.Education as DataEducation

class EducationMapper {

    fun from(
        data: EducationDTO,
    ): DataEducation = DataEducation(
        title = data.title ?: throw nullParameterException("education.title"),
        place = data.place ?: "",
        startDate = data.start_date ?: "",
        endDate = data.end_date ?: "",
        currently = data.currently ?: false,
    )

    fun from(
        data: DataEducation,
    ): Education = Education(
        title = data.title,
        place = data.place,
        startDate = data.startDate,
        endDate = data.endDate,
        currently = data.currently,
    )

    private fun nullParameterException(
        parameter: String
    ): IllegalArgumentException = IllegalArgumentException("$parameter can not be null")

}