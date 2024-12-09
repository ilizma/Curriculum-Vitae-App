package com.ilizma.work.di

import com.ilizma.work.domain.usecase.di.workUseCaseModule
import com.ilizma.work.flow.navigator.di.workScreenBackNavigatorModule
import com.ilizma.work.presentation.viewmodel.di.workScreenViewModelModule
import com.ilizma.work.view.router.di.workScreenRouterModule
import org.koin.core.module.Module

val workModules: List<Module> = listOf(
    workUseCaseModule,
    workScreenBackNavigatorModule,
    workScreenViewModelModule,
    workScreenRouterModule,
)