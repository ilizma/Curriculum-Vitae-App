package com.ilizma.personal.domain.usecase

import com.ilizma.curriculum.domain.repository.CurriculumVitaeRepository
import com.ilizma.personal.domain.mapper.SkillsMapper

class SkillsUseCaseImp(
    private val repository: CurriculumVitaeRepository,
    private val mapper: SkillsMapper,
) : SkillsUseCase {

    override suspend fun invoke(
    ): List<String> = repository.get()
        .let { mapper.from(it) }

}