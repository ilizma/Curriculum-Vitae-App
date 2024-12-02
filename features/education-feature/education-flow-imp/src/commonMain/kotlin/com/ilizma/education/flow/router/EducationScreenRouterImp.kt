package com.ilizma.education.flow.router

import com.ilizma.education.flow.navigator.EducationScreenBackCloseNavigator
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
    private val backCloseNavigator: EducationScreenBackCloseNavigator,
) : EducationScreenRouter {


    override fun init(
        coroutineScope: CoroutineScope,
        viewModel: EducationScreenViewModel,
    ) {
        coroutineScope.launch(dispatcher) {
            viewModel.navigationAction.collect {
                onNavigationAction(
                    action = it,
                )
            }
        }
    }

    private fun onNavigationAction(
        action: EducationScreenNavigationAction,
    ) {
        when (action) {
            Back -> backCloseNavigator.close()
        }
    }

}