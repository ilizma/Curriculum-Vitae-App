package com.ilizma.curriculum.di

import com.ilizma.curriculum.data.cache.di.curriculumVitaeCacheModule
import com.ilizma.curriculum.data.datasource.di.curriculumVitaeDataSourceModule
import com.ilizma.curriculum.data.repository.di.curriculumVitaeRepositoryModule
import org.koin.core.module.Module

val curriculumModules: List<Module> = listOf(
    curriculumVitaeCacheModule,
    curriculumVitaeDataSourceModule,
    curriculumVitaeRepositoryModule,
)