package com.ilizma.education.di

import com.ilizma.education.domain.usecase.di.complementaryEducationUseCaseModule
import com.ilizma.education.domain.usecase.di.educationUseCaseModule
import com.ilizma.education.flow.navigator.di.educationScreenBackCloseNavigatorModule
import com.ilizma.education.presentation.viewmodel.di.workScreenViewModelModule
import com.ilizma.education.view.router.di.educationScreenRouterModule
import org.koin.core.module.Module

val educationModules: List<Module> = listOf(
    educationUseCaseModule,
    complementaryEducationUseCaseModule,
    educationScreenBackCloseNavigatorModule,
    workScreenViewModelModule,
    educationScreenRouterModule,
)