package com.ilizma.personal.flow.navigator.di

import com.ilizma.personal.flow.navigator.PersonalDataScreenCloseNavigator
import com.ilizma.personal.flow.navigator.PersonalDataScreenCloseNavigatorImp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val personalDataScreenCloseNavigatorModule: Module = module {

    factory<PersonalDataScreenCloseNavigator> {
        PersonalDataScreenCloseNavigatorImp()
    }

}