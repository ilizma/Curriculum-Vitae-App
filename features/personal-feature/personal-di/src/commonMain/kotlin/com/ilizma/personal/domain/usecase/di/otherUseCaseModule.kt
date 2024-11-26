package com.ilizma.personal.domain.usecase.di

import com.ilizma.personal.domain.mapper.OtherMapper
import com.ilizma.personal.domain.usecase.OtherUseCase
import com.ilizma.personal.domain.usecase.OtherUseCaseImp
import org.koin.core.module.Module
import org.koin.dsl.module

val otherUseCaseModule: Module = module {

    factory<OtherUseCase> {
        OtherUseCaseImp(
            repository = get(),
            mapper = OtherMapper(),
        )
    }

}