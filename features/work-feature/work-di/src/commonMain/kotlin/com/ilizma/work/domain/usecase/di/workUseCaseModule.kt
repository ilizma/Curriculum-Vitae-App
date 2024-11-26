package com.ilizma.work.domain.usecase.di

import com.ilizma.work.domain.mapper.WorkMapper
import com.ilizma.work.domain.usecase.WorkUseCase
import com.ilizma.work.domain.usecase.WorkUseCaseImp
import org.koin.core.module.Module
import org.koin.dsl.module

val workUseCaseModule: Module = module {

    factory<WorkUseCase> {
        WorkUseCaseImp(
            repository = get(),
            mapper = WorkMapper(),
        )
    }

}