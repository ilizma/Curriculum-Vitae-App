package com.ilizma.personal.flow.navigator.di

import com.ilizma.personal.flow.navigator.PhoneNavigator
import com.ilizma.personal.flow.navigator.PhoneNavigatorImp
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val phoneNavigatorModule: Module = module {

    factory<PhoneNavigator> {
        PhoneNavigatorImp(
            context = androidContext(),
        )
    }
}