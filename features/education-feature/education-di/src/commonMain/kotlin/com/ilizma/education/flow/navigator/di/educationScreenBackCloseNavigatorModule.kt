package com.ilizma.education.flow.navigator.di

import com.ilizma.education.flow.navigator.EducationScreenBackCloseNavigator
import com.ilizma.education.flow.navigator.EducationScreenBackCloseNavigatorImp
import org.koin.core.module.Module
import org.koin.dsl.module

val educationScreenBackCloseNavigatorModule: Module = module {

    factory<EducationScreenBackCloseNavigator> {
        EducationScreenBackCloseNavigatorImp()
    }

}