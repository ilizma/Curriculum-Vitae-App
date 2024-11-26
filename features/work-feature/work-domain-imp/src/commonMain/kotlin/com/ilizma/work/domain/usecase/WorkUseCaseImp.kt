package com.ilizma.work.domain.usecase

import com.ilizma.curriculum.domain.repository.CurriculumVitaeRepository
import com.ilizma.work.domain.mapper.WorkMapper
import com.ilizma.work.domain.model.Work

class WorkUseCaseImp(
    private val repository: CurriculumVitaeRepository,
    private val mapper: WorkMapper,
) : WorkUseCase {

    override suspend fun invoke(
    ): List<Work> = repository.get()
        .let { mapper.from(it) }

}