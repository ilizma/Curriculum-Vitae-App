package com.ilizma.education.domain.usecase

import com.ilizma.education.domain.model.Education

interface EducationUseCase {

    suspend operator fun invoke(): List<Education>

}