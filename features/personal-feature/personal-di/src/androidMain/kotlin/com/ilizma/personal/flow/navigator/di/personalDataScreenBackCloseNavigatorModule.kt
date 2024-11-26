package com.ilizma.personal.flow.navigator.di

import com.ilizma.main.view.activity.MainActivity
import com.ilizma.personal.flow.navigator.PersonalDataScreenBackCloseNavigator
import com.ilizma.personal.flow.navigator.PersonalDataScreenBackCloseNavigatorImp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val personalDataScreenBackCloseNavigatorModule: Module = module {

    scope<MainActivity> {
        scoped<PersonalDataScreenBackCloseNavigator> {
            PersonalDataScreenBackCloseNavigatorImp(
                activity = get(),
            )
        }
    }

}