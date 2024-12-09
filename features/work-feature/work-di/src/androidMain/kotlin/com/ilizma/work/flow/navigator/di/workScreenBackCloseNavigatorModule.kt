package com.ilizma.work.flow.navigator.di

import com.ilizma.work.flow.navigator.WorkScreenBackNavigator
import com.ilizma.work.flow.navigator.WorkScreenBackNavigatorImp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val workScreenBackNavigatorModule: Module = module {

    factory<WorkScreenBackNavigator> {
        WorkScreenBackNavigatorImp()
    }

}