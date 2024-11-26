package com.ilizma.personal.domain.usecase

import com.ilizma.personal.domain.mapper.PersonalDataMapper
import com.ilizma.personal.domain.model.PersonalData
import com.ilizma.curriculum.domain.repository.CurriculumVitaeRepository

class PersonalDataUseCaseImp(
    private val repository: CurriculumVitaeRepository,
    private val mapper: PersonalDataMapper,
) : PersonalDataUseCase {

    override suspend fun invoke(
    ): PersonalData = repository.get()
        .let { mapper.from(it) }

}