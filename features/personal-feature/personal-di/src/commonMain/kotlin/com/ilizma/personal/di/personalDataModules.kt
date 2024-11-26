package com.ilizma.personal.di

import com.ilizma.personal.domain.usecase.di.descriptionUseCaseModule
import com.ilizma.personal.domain.usecase.di.otherUseCaseModule
import com.ilizma.personal.domain.usecase.di.personalDataUseCaseModule
import com.ilizma.personal.domain.usecase.di.skillsUseCaseModule
import com.ilizma.personal.flow.navigator.di.emailNavigatorModule
import com.ilizma.personal.flow.navigator.di.personalDataScreenBackCloseNavigatorModule
import com.ilizma.personal.flow.navigator.di.phoneNavigatorModule
import com.ilizma.personal.presentation.viewmodel.di.personalDataScreenViewModelModule
import com.ilizma.personal.view.router.di.personalDataScreenRouterModule
import org.koin.core.module.Module

val personalDataModules: List<Module> = listOf(
    personalDataUseCaseModule,
    descriptionUseCaseModule,
    skillsUseCaseModule,
    otherUseCaseModule,
    phoneNavigatorModule,
    emailNavigatorModule,
    personalDataScreenBackCloseNavigatorModule,
    personalDataScreenViewModelModule,
    personalDataScreenRouterModule,
)