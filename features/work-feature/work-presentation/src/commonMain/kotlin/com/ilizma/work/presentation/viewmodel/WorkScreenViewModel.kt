package com.ilizma.work.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.ilizma.work.presentation.model.WorkIntent
import com.ilizma.work.presentation.model.WorkScreenNavigationAction
import com.ilizma.work.presentation.model.WorkState
import kotlinx.coroutines.flow.Flow

abstract class WorkScreenViewModel : ViewModel() {

    abstract val workState: Flow<WorkState>

    abstract val navigationAction: Flow<WorkScreenNavigationAction>

    abstract fun onIntent(intent: WorkIntent)

}