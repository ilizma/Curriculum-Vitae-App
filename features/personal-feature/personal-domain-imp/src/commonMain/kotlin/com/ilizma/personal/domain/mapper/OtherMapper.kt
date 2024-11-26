package com.ilizma.personal.domain.mapper

import com.ilizma.curriculum.domain.model.CurriculumVitae
import com.ilizma.personal.domain.model.Other
import com.ilizma.curriculum.domain.model.Other as CurriculumOther

class OtherMapper {

    fun from(
        state: CurriculumVitae
    ): List<Other> = state.other.map { from(it) }

    fun from(
        other: CurriculumOther
    ): Other = Other(
        title = other.title,
        description = other.description,
        link = other.link,
    )
}