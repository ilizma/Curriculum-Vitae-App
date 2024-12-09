package com.ilizma.work.view.router

import androidx.navigation.NavHostController
import com.ilizma.work.presentation.viewmodel.WorkScreenViewModel
import kotlinx.coroutines.CoroutineScope

interface WorkScreenRouter {

    fun init(coroutineScope: CoroutineScope, navController: NavHostController, viewModel: WorkScreenViewModel)

}