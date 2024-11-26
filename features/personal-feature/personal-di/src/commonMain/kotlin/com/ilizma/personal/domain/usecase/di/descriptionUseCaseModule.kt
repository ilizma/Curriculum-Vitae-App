package com.ilizma.personal.domain.usecase.di

import com.ilizma.personal.domain.mapper.DescriptionMapper
import com.ilizma.personal.domain.usecase.DescriptionUseCase
import com.ilizma.personal.domain.usecase.DescriptionUseCaseImp
import org.koin.core.module.Module
import org.koin.dsl.module

val descriptionUseCaseModule: Module = module {

    factory<DescriptionUseCase> {
        DescriptionUseCaseImp(
            repository = get(),
            mapper = DescriptionMapper(),
        )
    }

}