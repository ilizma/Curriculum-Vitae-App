package com.ilizma.personal.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.ilizma.personal.domain.model.Other
import com.ilizma.personal.domain.model.PersonalData
import com.ilizma.personal.domain.usecase.DescriptionUseCase
import com.ilizma.personal.domain.usecase.OtherUseCase
import com.ilizma.personal.domain.usecase.PersonalDataUseCase
import com.ilizma.personal.domain.usecase.SkillsUseCase
import com.ilizma.personal.presentation.mapper.PersonalDataMapper
import com.ilizma.personal.presentation.model.PersonalDataIntent
import com.ilizma.personal.presentation.model.PersonalDataScreenNavigationAction
import com.ilizma.personal.presentation.model.PersonalDataState
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

class PersonalDataScreenViewModelImp(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val personalDataUseCase: PersonalDataUseCase,
    private val descriptionUseCase: DescriptionUseCase,
    private val skillsUseCase: SkillsUseCase,
    private val otherUseCase: OtherUseCase,
    private val mapper: PersonalDataMapper,
    private val isDebug: Boolean,
    private val _personalDataState: MutableStateFlow<PersonalDataState>,
    private val _navigationAction: MutableSharedFlow<PersonalDataScreenNavigationAction>,
) : PersonalDataScreenViewModel() {

    override val navigationAction: Flow<PersonalDataScreenNavigationAction> = _navigationAction

    init {
        getPersonalData()
    }

    override val personalDataState: Flow<PersonalDataState> = _personalDataState

    override fun onIntent(intent: PersonalDataIntent) {
        when (intent) {
            is PersonalDataIntent.Phone -> onPhone(
                phone = intent.phone,
            )

            is PersonalDataIntent.Email -> onEmail(
                email = intent.email,
            )

            PersonalDataIntent.Retry -> getPersonalData()
            PersonalDataIntent.Back -> onBack()
        }
    }

    private fun onPhone(
        phone: String,
    ) {
        viewModelScope.launch(dispatcher) {
            PersonalDataScreenNavigationAction.Phone(
                phone = phone
            ).let { _navigationAction.emit(it) }
        }
    }

    private fun onEmail(
        email: String,
    ) {
        viewModelScope.launch(dispatcher) {
            PersonalDataScreenNavigationAction.Email(
                email = email
            ).let { _navigationAction.emit(it) }
        }
    }

    private fun getPersonalData() {
        viewModelScope.launch(dispatcher) {
            try {
                onPersonalDataState(
                    personalData = personalDataUseCase(),
                    description = descriptionUseCase(),
                    skills = skillsUseCase(),
                    other = otherUseCase(),
                )
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    private fun onBack() {
        viewModelScope.launch(dispatcher) {
            _navigationAction.emit(PersonalDataScreenNavigationAction.Back)
        }
    }

    private suspend fun onPersonalDataState(
        personalData: PersonalData,
        description: String,
        skills: List<String>,
        other: List<Other>,
    ) {
        mapper.from(
            data = personalData,
            description = description,
            skills = skills,
            other = other
        ).let { _personalDataState.emit(it) }

    }

    private suspend fun onError(
        throwable: Throwable,
    ) {
        when (isDebug) {
            true -> throwable.message ?: getString(Res.string.unknown_error)
            false -> getString(Res.string.unknown_error)
        }.let { PersonalDataState.Error(it) }
            .let { _personalDataState.emit(it) }
    }

}
