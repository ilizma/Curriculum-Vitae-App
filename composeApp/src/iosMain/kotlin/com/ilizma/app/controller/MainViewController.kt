package com.ilizma.app.controller

import androidx.compose.ui.window.ComposeUIViewController
import com.ilizma.app.di.initKoin
import com.ilizma.main.view.compose.AppNavigation
import org.koin.compose.koinInject

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() },
) {
    AppNavigation(
        personalDataScreenRouter = koinInject(),
    )
}