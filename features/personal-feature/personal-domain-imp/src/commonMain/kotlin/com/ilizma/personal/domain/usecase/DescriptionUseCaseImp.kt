package com.ilizma.personal.domain.usecase

import com.ilizma.curriculum.domain.repository.CurriculumVitaeRepository
import com.ilizma.personal.domain.mapper.DescriptionMapper

class DescriptionUseCaseImp(
    private val repository: CurriculumVitaeRepository,
    private val mapper: DescriptionMapper,
) : DescriptionUseCase {

    override suspend fun invoke(
    ): String = repository.get()
        .let { mapper.from(it) }

}