package com.ilizma.personal.domain.usecase.di

import com.ilizma.personal.domain.usecase.SkillsUseCase
import com.ilizma.personal.domain.usecase.SkillsUseCaseImp
import org.koin.core.module.Module
import org.koin.dsl.module

val skillsUseCaseModule: Module = module {

    factory<SkillsUseCase> {
        SkillsUseCaseImp(
            repository = get(),
        )
    }

}