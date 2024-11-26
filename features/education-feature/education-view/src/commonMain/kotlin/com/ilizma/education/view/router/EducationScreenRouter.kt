package com.ilizma.education.view.router

import com.ilizma.education.presentation.viewmodel.EducationScreenViewModel
import kotlinx.coroutines.CoroutineScope

interface EducationScreenRouter {

    fun init(coroutineScope: CoroutineScope, viewModel: EducationScreenViewModel)

}