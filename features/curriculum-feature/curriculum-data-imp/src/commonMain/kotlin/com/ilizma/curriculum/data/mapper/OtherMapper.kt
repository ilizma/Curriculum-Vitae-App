package com.ilizma.curriculum.data.mapper

import com.ilizma.curriculum.data.model.OtherDTO
import com.ilizma.curriculum.domain.model.Other
import com.ilizma.curriculum.data.model.Other as DataOther

class OtherMapper {

    fun from(
        data: OtherDTO,
    ): DataOther = DataOther(
        title = data.title ?: throw nullParameterException("other.title"),
        description = data.description ?: "",
        link = data.link ?: "",
    )

    fun from(
        data: DataOther,
    ): Other = Other(
        title = data.title,
        description = data.description,
        link = data.link,
    )

    private fun nullParameterException(
        parameter: String
    ): IllegalArgumentException = IllegalArgumentException("$parameter can not be null")

}