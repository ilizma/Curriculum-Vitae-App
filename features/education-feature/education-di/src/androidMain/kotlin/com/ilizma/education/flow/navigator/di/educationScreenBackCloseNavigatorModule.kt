package com.ilizma.education.flow.navigator.di

import com.ilizma.education.flow.navigator.EducationScreenBackNavigator
import com.ilizma.education.flow.navigator.EducationScreenBackNavigatorImp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val educationScreenBackNavigatorModule: Module = module {

    factory<EducationScreenBackNavigator> {
        EducationScreenBackNavigatorImp()
    }

}