package com.ilizma.education.flow.router

import androidx.navigation.NavHostController
import com.ilizma.education.flow.navigator.EducationScreenBackNavigator
import com.ilizma.education.presentation.model.EducationScreenNavigationAction
import com.ilizma.education.presentation.model.EducationScreenNavigationAction.Back
import com.ilizma.education.presentation.viewmodel.EducationScreenViewModel
import com.ilizma.education.view.router.EducationScreenRouter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EducationScreenRouterImp(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val backNavigator: EducationScreenBackNavigator,
) : EducationScreenRouter {


    override fun init(
        coroutineScope: CoroutineScope,
        navController: NavHostController,
        viewModel: EducationScreenViewModel,
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
        action: EducationScreenNavigationAction,
        navController: NavHostController,
    ) {
        when (action) {
            Back -> backNavigator.back(
                navController = navController,
            )
        }
    }

}