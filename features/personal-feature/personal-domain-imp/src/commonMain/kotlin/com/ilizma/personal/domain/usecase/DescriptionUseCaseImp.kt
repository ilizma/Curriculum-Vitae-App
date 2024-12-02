package com.ilizma.personal.domain.usecase

import com.ilizma.curriculum.domain.repository.CurriculumVitaeRepository

class DescriptionUseCaseImp(
    private val repository: CurriculumVitaeRepository,
) : DescriptionUseCase {

    override suspend fun invoke(
    ): String = repository.get()
        .let { it.description }

}