package com.ilizma.personal.domain.usecase

import com.ilizma.curriculum.domain.repository.CurriculumVitaeRepository
import com.ilizma.personal.domain.mapper.OtherMapper
import com.ilizma.personal.domain.model.Other

class OtherUseCaseImp(
    private val repository: CurriculumVitaeRepository,
    private val mapper: OtherMapper,
) : OtherUseCase {

    override suspend fun invoke(
    ): List<Other> = repository.get()
        .let { it.other }
        .let { mapper.from(it) }

}