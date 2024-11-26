package com.ilizma.personal.presentation.viewmodel.di

import com.ilizma.personal.presentation.mapper.PersonalDataMapper
import com.ilizma.personal.presentation.model.PersonalDataState
import com.ilizma.personal.presentation.viewmodel.PersonalDataScreenViewModel
import com.ilizma.personal.presentation.viewmodel.PersonalDataScreenViewModelImp
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
actual val personalDataScreenViewModelModule: Module = module {

    factory<PersonalDataScreenViewModel> {
        PersonalDataScreenViewModelImp(
            personalDataUseCase = get(),
            descriptionUseCase = get(),
            skillsUseCase = get(),
            otherUseCase = get(),
            mapper = PersonalDataMapper(),
            isDebug = Platform.isDebugBinary,
            _personalDataState = MutableStateFlow(PersonalDataState.Loading),
            _navigationAction = MutableSharedFlow(),
        )
    }
}