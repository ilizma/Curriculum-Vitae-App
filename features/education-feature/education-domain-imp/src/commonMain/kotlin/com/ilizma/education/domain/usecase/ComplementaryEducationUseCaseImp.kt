package com.ilizma.education.domain.usecase

import com.ilizma.curriculum.domain.repository.CurriculumVitaeRepository
import com.ilizma.education.domain.mapper.ComplementaryEducationMapper
import com.ilizma.education.domain.model.ComplementaryEducation

class ComplementaryEducationUseCaseImp(
    private val repository: CurriculumVitaeRepository,
    private val mapper: ComplementaryEducationMapper,
) : ComplementaryEducationUseCase {

    override suspend fun invoke(
    ): List<ComplementaryEducation> = repository.get()
        .let { it.complementaryEducation }
        .let { mapper.from(it) }

}