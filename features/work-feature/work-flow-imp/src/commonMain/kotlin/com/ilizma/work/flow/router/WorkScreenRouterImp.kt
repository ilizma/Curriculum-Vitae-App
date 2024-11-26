package com.ilizma.work.flow.router

import com.ilizma.work.flow.navigator.WorkScreenBackCloseNavigator
import com.ilizma.work.presentation.model.WorkScreenNavigationAction
import com.ilizma.work.presentation.model.WorkScreenNavigationAction.Back
import com.ilizma.work.presentation.viewmodel.WorkScreenViewModel
import com.ilizma.work.view.router.WorkScreenRouter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkScreenRouterImp(
    private val backCloseNavigator: WorkScreenBackCloseNavigator,
) : WorkScreenRouter {


    override fun init(
        coroutineScope: CoroutineScope,
        viewModel: WorkScreenViewModel,
    ) {
        coroutineScope.launch(Dispatchers.Main) {
            viewModel.navigationAction.collect {
                onNavigationAction(
                    action = it,
                )
            }
        }
    }

    private fun onNavigationAction(
        action: WorkScreenNavigationAction,
    ) {
        when (action) {
            Back -> backCloseNavigator.close()
        }
    }

}