package com.ilizma.personal.view.router

import com.ilizma.personal.presentation.viewmodel.PersonalDataScreenViewModel
import kotlinx.coroutines.CoroutineScope

interface PersonalDataScreenRouter {

    fun init(coroutineScope: CoroutineScope, viewModel: PersonalDataScreenViewModel)

}