package com.ilizma.app.application

import android.app.Application
import cat.ereza.customactivityoncrash.config.CaocConfig
import com.google.firebase.Firebase
import com.google.firebase.initialize
import com.ilizma.app.di.initKoin
import com.ilizma.errormanagement.di.errorManagementModules
import com.ilizma.errormanagement.view.activity.CrashActivity
import com.ilizma.main.di.mainModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.core.module.Module

class CurriculumVitaeAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)

        initKoin(
            config = {
                androidLogger(Level.DEBUG)
                androidContext(this@CurriculumVitaeAppApplication)
            },
            platformModules = mutableListOf<Module>()
                .apply {
                    addAll(mainModules)
                    addAll(errorManagementModules)
                }
        )

        CaocConfig.Builder.create()
            .errorActivity(CrashActivity::class.java)
            .apply()
    }

}