package com.ilizma.education.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.ilizma.education.presentation.model.EducationIntent
import com.ilizma.education.presentation.model.EducationScreenNavigationAction
import com.ilizma.education.presentation.model.EducationState
import kotlinx.coroutines.flow.Flow

abstract class EducationScreenViewModel : ViewModel() {

    abstract val educationState: Flow<EducationState>

    abstract val navigationAction: Flow<EducationScreenNavigationAction>

    abstract fun onIntent(intent: EducationIntent)

}