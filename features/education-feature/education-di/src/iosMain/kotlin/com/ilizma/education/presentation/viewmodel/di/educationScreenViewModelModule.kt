package com.ilizma.education.presentation.viewmodel.di

import com.ilizma.education.presentation.mapper.EducationMapper
import com.ilizma.education.presentation.model.EducationState
import com.ilizma.education.presentation.viewmodel.EducationScreenViewModel
import com.ilizma.education.presentation.viewmodel.EducationScreenViewModelImp
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
actual val workScreenViewModelModule: Module = module {

    factory<EducationScreenViewModel> {
        EducationScreenViewModelImp(
            educationUseCase = get(),
            complementaryEducationUseCase = get(),
            mapper = EducationMapper(),
            isDebug = Platform.isDebugBinary,
            _educationState = MutableStateFlow(EducationState.Loading),
            _navigationAction = MutableSharedFlow(),
        )
    }
}