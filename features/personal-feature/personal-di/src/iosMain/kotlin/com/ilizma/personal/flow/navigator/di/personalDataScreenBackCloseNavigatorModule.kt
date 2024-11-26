package com.ilizma.personal.flow.navigator.di

import com.ilizma.personal.flow.navigator.PersonalDataScreenBackCloseNavigator
import com.ilizma.personal.flow.navigator.PersonalDataScreenBackCloseNavigatorImp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val personalDataScreenBackCloseNavigatorModule: Module = module {

    factory<PersonalDataScreenBackCloseNavigator> {
        PersonalDataScreenBackCloseNavigatorImp()
    }

}