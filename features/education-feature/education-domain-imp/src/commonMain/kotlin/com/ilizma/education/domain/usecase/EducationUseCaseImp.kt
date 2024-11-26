package com.ilizma.education.domain.usecase

import com.ilizma.education.domain.mapper.EducationMapper
import com.ilizma.education.domain.model.Education
import com.ilizma.curriculum.domain.repository.CurriculumVitaeRepository

class EducationUseCaseImp(
    private val repository: CurriculumVitaeRepository,
    private val mapper: EducationMapper,
) : EducationUseCase {

    override suspend fun invoke(
    ): List<Education> = repository.get()
        .let { mapper.from(it) }

}