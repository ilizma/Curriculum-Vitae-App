package com.ilizma.work.presentation.viewmodel.di

import com.ilizma.work.presentation.mapper.WorkMapper
import com.ilizma.work.presentation.model.WorkState
import com.ilizma.work.presentation.viewmodel.WorkScreenViewModel
import com.ilizma.work.presentation.viewmodel.WorkScreenViewModelImp
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
actual val workScreenViewModelModule: Module = module {

    factory<WorkScreenViewModel> {
        WorkScreenViewModelImp(
            useCase = get(),
            mapper = WorkMapper(),
            isDebug = Platform.isDebugBinary,
            _educationState = MutableStateFlow(WorkState.Loading),
            _navigationAction = MutableSharedFlow(),
        )
    }
}