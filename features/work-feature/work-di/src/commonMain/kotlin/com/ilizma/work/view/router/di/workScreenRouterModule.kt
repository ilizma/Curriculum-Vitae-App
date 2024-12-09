package com.ilizma.work.view.router.di

import com.ilizma.work.flow.router.WorkScreenRouterImp
import com.ilizma.work.view.router.WorkScreenRouter
import org.koin.core.module.Module
import org.koin.dsl.module

val workScreenRouterModule: Module = module {

    factory<WorkScreenRouter> {
        WorkScreenRouterImp(
            backNavigator = get(),
        )
    }

}