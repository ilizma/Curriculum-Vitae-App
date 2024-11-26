package com.ilizma.education.domain.usecase.di

import com.ilizma.education.domain.mapper.EducationMapper
import com.ilizma.education.domain.usecase.EducationUseCase
import com.ilizma.education.domain.usecase.EducationUseCaseImp
import org.koin.core.module.Module
import org.koin.dsl.module

val educationUseCaseModule: Module = module {

    factory<EducationUseCase> {
        EducationUseCaseImp(
            repository = get(),
            mapper = EducationMapper(),
        )
    }

}