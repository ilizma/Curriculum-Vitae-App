package com.ilizma.personal.domain.mapper

import com.ilizma.curriculum.domain.model.CurriculumVitae

class DescriptionMapper {

    fun from(
        state: CurriculumVitae
    ): String = state.description
}