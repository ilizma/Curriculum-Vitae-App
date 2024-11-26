package com.ilizma.personal.domain.usecase

import com.ilizma.personal.domain.model.PersonalData

interface PersonalDataUseCase {

    suspend operator fun invoke(): PersonalData

}