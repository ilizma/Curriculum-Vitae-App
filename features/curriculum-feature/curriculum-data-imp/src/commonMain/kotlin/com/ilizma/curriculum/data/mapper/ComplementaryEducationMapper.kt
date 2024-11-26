package com.ilizma.curriculum.data.mapper

import com.ilizma.api.model.ComplementaryEducationDTO
import com.ilizma.curriculum.domain.model.ComplementaryEducation
import com.ilizma.curriculum.data.model.ComplementaryEducation as DataComplementaryEducation

class ComplementaryEducationMapper {

    fun from(
        data: ComplementaryEducationDTO,
    ): DataComplementaryEducation = DataComplementaryEducation(
        type = data.type ?: "",
        title = data.title ?: throw nullParameterException("complementaryEducation.title"),
        hours = data.hours ?: "",
        date = data.date ?: "",
        place = data.place ?: "",
    )

    fun from(
        data: DataComplementaryEducation,
    ): ComplementaryEducation = ComplementaryEducation(
        type = data.type,
        title = data.title,
        hours = data.hours,
        date = data.date,
        place = data.place,
    )

    private fun nullParameterException(
        parameter: String
    ): IllegalArgumentException = IllegalArgumentException("$parameter can not be null")

}