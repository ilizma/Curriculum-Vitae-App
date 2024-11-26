package com.ilizma.work.flow.navigator.di

import com.ilizma.work.flow.navigator.WorkScreenBackCloseNavigator
import com.ilizma.work.flow.navigator.WorkScreenBackCloseNavigatorImp
import org.koin.core.module.Module
import org.koin.dsl.module

val workScreenBackCloseNavigatorModule: Module = module {

    factory<WorkScreenBackCloseNavigator> {
        WorkScreenBackCloseNavigatorImp()
    }

}