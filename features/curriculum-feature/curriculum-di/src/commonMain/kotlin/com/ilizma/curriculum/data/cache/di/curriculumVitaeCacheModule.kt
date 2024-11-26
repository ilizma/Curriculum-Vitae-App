package com.ilizma.curriculum.data.cache.di

import com.ilizma.curriculum.data.cache.CurriculumVitaeCache
import com.ilizma.curriculum.data.cache.CurriculumVitaeCacheImp
import org.koin.core.module.Module
import org.koin.dsl.module

val curriculumVitaeCacheModule: Module = module {

    single<CurriculumVitaeCache> { CurriculumVitaeCacheImp() }

}