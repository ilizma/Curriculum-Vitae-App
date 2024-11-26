package com.ilizma.work.view.router

import com.ilizma.work.presentation.viewmodel.WorkScreenViewModel
import kotlinx.coroutines.CoroutineScope

interface WorkScreenRouter {

    fun init(coroutineScope: CoroutineScope, viewModel: WorkScreenViewModel)

}