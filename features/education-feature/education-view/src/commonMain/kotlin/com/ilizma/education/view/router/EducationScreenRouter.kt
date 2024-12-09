package com.ilizma.education.view.router

import androidx.navigation.NavHostController
import com.ilizma.education.presentation.viewmodel.EducationScreenViewModel
import kotlinx.coroutines.CoroutineScope

interface EducationScreenRouter {

    fun init(coroutineScope: CoroutineScope, navController: NavHostController, viewModel: EducationScreenViewModel)

}