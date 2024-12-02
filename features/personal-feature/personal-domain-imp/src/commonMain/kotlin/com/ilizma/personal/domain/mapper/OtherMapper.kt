package com.ilizma.personal.domain.mapper

import com.ilizma.personal.domain.model.Other
import com.ilizma.curriculum.domain.model.Other as CurriculumOther

class OtherMapper {

    fun from(
        data: List<CurriculumOther>,
    ): List<Other> = data.map { from(it) }

    fun from(
        other: CurriculumOther
    ): Other = Other(
        title = other.title,
        description = other.description,
        link = other.link,
    )
}