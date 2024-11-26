package com.ilizma.curriculum.data.datasource

import com.ilizma.curriculum.data.model.CurriculumVitae

interface CurriculumVitaeDataSource {

    suspend fun get(): CurriculumVitae

}