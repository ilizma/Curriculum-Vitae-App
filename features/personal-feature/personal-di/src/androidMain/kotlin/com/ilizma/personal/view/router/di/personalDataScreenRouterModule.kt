package com.ilizma.personal.view.router.di

import com.ilizma.main.view.activity.MainActivity
import com.ilizma.personal.flow.router.PersonalDataScreenRouterImp
import com.ilizma.personal.view.router.PersonalDataScreenRouter
import org.koin.core.module.Module
import org.koin.dsl.module

actual val personalDataScreenRouterModule: Module = module {

    scope<MainActivity> {
        scoped<PersonalDataScreenRouter> {
            PersonalDataScreenRouterImp(
                phoneNavigator = get(),
                emailNavigator = get(),
                closeNavigator = get(),
            )
        }
    }

}