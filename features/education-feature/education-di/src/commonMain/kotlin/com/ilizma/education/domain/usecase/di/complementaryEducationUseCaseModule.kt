package com.ilizma.education.domain.usecase.di

import com.ilizma.education.domain.mapper.ComplementaryEducationMapper
import com.ilizma.education.domain.usecase.ComplementaryEducationUseCase
import com.ilizma.education.domain.usecase.ComplementaryEducationUseCaseImp
import org.koin.core.module.Module
import org.koin.dsl.module

val complementaryEducationUseCaseModule: Module = module {

    factory<ComplementaryEducationUseCase> {
        ComplementaryEducationUseCaseImp(
            repository = get(),
            mapper = ComplementaryEducationMapper(),
        )
    }

}