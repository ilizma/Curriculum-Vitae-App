package com.ilizma.work.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.ilizma.resources.Res
import com.ilizma.resources.unknown_error
import com.ilizma.work.domain.model.Work
import com.ilizma.work.domain.usecase.WorkUseCase
import com.ilizma.work.presentation.mapper.WorkMapper
import com.ilizma.work.presentation.model.WorkIntent
import com.ilizma.work.presentation.model.WorkScreenNavigationAction
import com.ilizma.work.presentation.model.WorkScreenNavigationAction.Back
import com.ilizma.work.presentation.model.WorkState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString

class WorkScreenViewModelImp(
    private val useCase: WorkUseCase,
    private val mapper: WorkMapper,
    private val isDebug: Boolean,
    private val _educationState: MutableStateFlow<WorkState>,
    private val _navigationAction: MutableSharedFlow<WorkScreenNavigationAction>,
) : WorkScreenViewModel() {

    override val navigationAction: Flow<WorkScreenNavigationAction> = _navigationAction

    init {
        getWork()
    }

    override val workState: Flow<WorkState> = _educationState

    override fun onIntent(intent: WorkIntent) {
        when (intent) {
            WorkIntent.Retry -> getWork()
            WorkIntent.Back -> onBack()
        }
    }

    private fun getWork() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                useCase()
                    .let { onEducationState(it) }
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    private fun onBack() {
        viewModelScope.launch(Dispatchers.IO) { _navigationAction.emit(Back) }
    }

    private suspend fun onEducationState(
        workList: List<Work>,
    ) {
        mapper.from(workList)
            .let { _educationState.emit(it) }

    }

    private suspend fun onError(
        throwable: Throwable,
    ) {
        when (isDebug) {
            true -> throwable.message ?: getString(Res.string.unknown_error)
            false -> getString(Res.string.unknown_error)
        }.let { WorkState.Error(it) }
            .let { _educationState.emit(it) }
    }

}
