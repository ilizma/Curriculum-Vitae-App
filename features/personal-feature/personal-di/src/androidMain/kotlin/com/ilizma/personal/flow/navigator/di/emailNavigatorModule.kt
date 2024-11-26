package com.ilizma.personal.flow.navigator.di

import com.ilizma.personal.flow.navigator.EmailNavigator
import com.ilizma.personal.flow.navigator.EmailNavigatorImp
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val emailNavigatorModule: Module = module {

    factory<EmailNavigator> {
        EmailNavigatorImp(
            context = androidContext(),
        )
    }
}