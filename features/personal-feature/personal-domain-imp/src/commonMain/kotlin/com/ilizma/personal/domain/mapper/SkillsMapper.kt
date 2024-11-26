package com.ilizma.personal.domain.mapper

import com.ilizma.curriculum.domain.model.CurriculumVitae

class SkillsMapper {

    fun from(
        state: CurriculumVitae
    ): List<String> = state.skills
}