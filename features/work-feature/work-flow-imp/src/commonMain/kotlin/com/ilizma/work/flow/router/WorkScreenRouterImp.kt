package com.ilizma.work.flow.router

import androidx.navigation.NavHostController
import com.ilizma.work.flow.navigator.WorkScreenBackNavigator
import com.ilizma.work.presentation.model.WorkScreenNavigationAction
import com.ilizma.work.presentation.model.WorkScreenNavigationAction.Back
import com.ilizma.work.presentation.viewmodel.WorkScreenViewModel
import com.ilizma.work.view.router.WorkScreenRouter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkScreenRouterImp(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val backNavigator: WorkScreenBackNavigator,
) : WorkScreenRouter {


    override fun init(
        coroutineScope: CoroutineScope,
        navController: NavHostController,
        viewModel: WorkScreenViewModel,
    ) {
        coroutineScope.launch(dispatcher) {
            viewModel.navigationAction.collect {
                onNavigationAction(
                    action = it,
                    navController = navController,
                )
            }
        }
    }

    private fun onNavigationAction(
        action: WorkScreenNavigationAction,
        navController: NavHostController,
    ) {
        when (action) {
            Back -> backNavigator.back(navController)
        }
    }

}