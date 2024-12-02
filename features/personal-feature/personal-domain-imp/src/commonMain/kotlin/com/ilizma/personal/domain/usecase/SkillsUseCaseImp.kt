package com.ilizma.personal.domain.usecase

import com.ilizma.curriculum.domain.repository.CurriculumVitaeRepository

class SkillsUseCaseImp(
    private val repository: CurriculumVitaeRepository,
) : SkillsUseCase {

    override suspend fun invoke(
    ): List<String> = repository.get()
        .let { it.skills }

}