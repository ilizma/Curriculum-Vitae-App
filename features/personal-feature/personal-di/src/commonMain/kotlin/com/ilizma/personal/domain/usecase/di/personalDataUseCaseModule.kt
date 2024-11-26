package com.ilizma.personal.domain.usecase.di

import com.ilizma.personal.domain.mapper.PersonalDataMapper
import com.ilizma.personal.domain.usecase.PersonalDataUseCase
import com.ilizma.personal.domain.usecase.PersonalDataUseCaseImp
import org.koin.core.module.Module
import org.koin.dsl.module

val personalDataUseCaseModule: Module = module {

    factory<PersonalDataUseCase> {
        PersonalDataUseCaseImp(
            repository = get(),
            mapper = PersonalDataMapper(),
        )
    }

}