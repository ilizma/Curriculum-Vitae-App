package com.ilizma.curriculum.domain.repository

import com.ilizma.curriculum.domain.model.CurriculumVitae

interface CurriculumVitaeRepository {

    suspend fun get(): CurriculumVitae

}