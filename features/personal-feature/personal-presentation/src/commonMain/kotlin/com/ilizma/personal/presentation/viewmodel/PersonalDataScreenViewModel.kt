package com.ilizma.personal.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.ilizma.personal.presentation.model.PersonalDataIntent
import com.ilizma.personal.presentation.model.PersonalDataScreenNavigationAction
import com.ilizma.personal.presentation.model.PersonalDataState
import kotlinx.coroutines.flow.Flow

abstract class PersonalDataScreenViewModel : ViewModel() {

    abstract val personalDataState: Flow<PersonalDataState>

    abstract val navigationAction: Flow<PersonalDataScreenNavigationAction>

    abstract fun onIntent(intent: PersonalDataIntent)

}