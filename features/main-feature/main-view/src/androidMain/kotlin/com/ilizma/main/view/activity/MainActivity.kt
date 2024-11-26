package com.ilizma.main.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ilizma.main.view.compose.AppNavigation
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import org.koin.core.scope.Scope

class MainActivity : ComponentActivity(), AndroidScopeComponent {

    override val scope: Scope by activityScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KoinContext {
                AppNavigation(
                    personalDataScreenRouter = koinInject(scope = scope)
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.close()
    }
}