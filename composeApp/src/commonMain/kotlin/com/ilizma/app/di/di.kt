package com.ilizma.app.di

import com.ilizma.curriculum.di.curriculumModules
import com.ilizma.education.di.educationModules
import com.ilizma.personal.di.personalDataModules
import com.ilizma.work.di.workModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    config: KoinAppDeclaration? = null,
    platformModules: List<Module>? = null,
) {
    startKoin {
        config?.invoke(this)
        mutableListOf<Module>()
            .apply {
                addAll(curriculumModules)
                addAll(personalDataModules)
                addAll(educationModules)
                addAll(workModules)
                platformModules?.let { addAll(it) }
            }.let { modules(it) }
    }
}