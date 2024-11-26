package com.ilizma.education.domain.usecase

import com.ilizma.education.domain.model.ComplementaryEducation

interface ComplementaryEducationUseCase {

    suspend operator fun invoke(): List<ComplementaryEducation>

}