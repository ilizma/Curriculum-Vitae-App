package com.ilizma.education.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.ilizma.education.domain.model.ComplementaryEducation
import com.ilizma.education.domain.model.Education
import com.ilizma.education.domain.usecase.ComplementaryEducationUseCase
import com.ilizma.education.domain.usecase.EducationUseCase
import com.ilizma.education.presentation.mapper.EducationMapper
import com.ilizma.education.presentation.model.EducationIntent
import com.ilizma.education.presentation.model.EducationScreenNavigationAction
import com.ilizma.education.presentation.model.EducationScreenNavigationAction.Back
import com.ilizma.education.presentation.model.EducationState
import com.ilizma.resources.Res
import com.ilizma.resources.unknown_error
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString

class EducationScreenViewModelImp(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val educationUseCase: EducationUseCase,
    private val complementaryEducationUseCase: ComplementaryEducationUseCase,
    private val mapper: EducationMapper,
    private val isDebug: Boolean,
    private val _educationState: MutableStateFlow<EducationState>,
    private val _navigationAction: MutableSharedFlow<EducationScreenNavigationAction>,
) : EducationScreenViewModel() {

    override val navigationAction: Flow<EducationScreenNavigationAction> = _navigationAction

    init {
        getEducation()
    }

    override val educationState: Flow<EducationState> = _educationState

    override fun onIntent(intent: EducationIntent) {
        when (intent) {
            EducationIntent.Retry -> getEducation()
            EducationIntent.Back -> onBack()
        }
    }

    private fun getEducation() {
        viewModelScope.launch(dispatcher) {
            try {
                onEducationState(
                    educationList = educationUseCase(),
                    complementaryEducationList = complementaryEducationUseCase(),
                )
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    private fun onBack() {
        viewModelScope.launch(dispatcher) { _navigationAction.emit(Back) }
    }

    private suspend fun onEducationState(
        educationList: List<Education>,
        complementaryEducationList: List<ComplementaryEducation>,
    ) {
        mapper.from(
            educationList = educationList,
            complementaryEducationList = complementaryEducationList,
        ).let { _educationState.emit(it) }

    }

    private suspend fun onError(
        throwable: Throwable,
    ) {
        when (isDebug) {
            true -> throwable.message ?: getString(Res.string.unknown_error)
            false -> getString(Res.string.unknown_error)
        }.let { EducationState.Error(it) }
            .let { _educationState.emit(it) }
    }

}
