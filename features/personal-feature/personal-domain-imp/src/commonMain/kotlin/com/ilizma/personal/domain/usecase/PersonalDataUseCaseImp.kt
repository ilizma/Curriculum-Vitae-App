package com.ilizma.personal.domain.usecase

import com.ilizma.curriculum.domain.repository.CurriculumVitaeRepository
import com.ilizma.personal.domain.mapper.PersonalDataMapper
import com.ilizma.personal.domain.model.PersonalData

class PersonalDataUseCaseImp(
    private val repository: CurriculumVitaeRepository,
    private val mapper: PersonalDataMapper,
) : PersonalDataUseCase {

    override suspend fun invoke(
    ): PersonalData = repository.get()
        .let { it.personalData }
        .let { mapper.from(it) }

}