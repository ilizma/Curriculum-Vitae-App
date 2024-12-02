package com.ilizma.personal.flow.router

import com.ilizma.personal.flow.navigator.EmailNavigator
import com.ilizma.personal.flow.navigator.PersonalDataScreenBackCloseNavigator
import com.ilizma.personal.flow.navigator.PhoneNavigator
import com.ilizma.personal.presentation.model.PersonalDataScreenNavigationAction
import com.ilizma.personal.presentation.model.PersonalDataScreenNavigationAction.Back
import com.ilizma.personal.presentation.viewmodel.PersonalDataScreenViewModel
import com.ilizma.personal.view.router.PersonalDataScreenRouter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonalDataScreenRouterImp(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val phoneNavigator: PhoneNavigator,
    private val emailNavigator: EmailNavigator,
    private val backCloseNavigator: PersonalDataScreenBackCloseNavigator,
) : PersonalDataScreenRouter {


    override fun init(
        coroutineScope: CoroutineScope,
        viewModel: PersonalDataScreenViewModel,
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
        action: PersonalDataScreenNavigationAction,
    ) {
        when (action) {
            is PersonalDataScreenNavigationAction.Phone -> phoneNavigator.navigate(
                phoneNumber = action.phone,
            )

            is PersonalDataScreenNavigationAction.Email -> emailNavigator.navigate(
                email = action.email,
            )

            Back -> backCloseNavigator.close()
        }
    }

}