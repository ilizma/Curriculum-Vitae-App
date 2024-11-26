package com.ilizma.education.view.router.di

import com.ilizma.education.flow.router.EducationScreenRouterImp
import com.ilizma.education.view.router.EducationScreenRouter
import org.koin.core.module.Module
import org.koin.dsl.module

val educationScreenRouterModule: Module = module {

    factory<EducationScreenRouter> {
        EducationScreenRouterImp(
            backCloseNavigator = get(),
        )
    }

}