package com.ilizma.personal.view.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import com.ilizma.personal.presentation.model.PersonalDataIntent
import com.ilizma.personal.presentation.model.PersonalDataState
import com.ilizma.personal.presentation.viewmodel.PersonalDataScreenViewModel
import com.ilizma.view.lifecycle.collectAsStateMultiplatform

@Composable
actual fun PersonalDataScreen(
    viewModel: PersonalDataScreenViewModel,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
) {
    viewModel.personalDataState
        .collectAsStateMultiplatform(
            initialValue = PersonalDataState.Loading,
        ).value
        .let { state ->
            ScreenState(
                state = state,
                snackbarHostState = snackbarHostState,
                paddingValues = paddingValues,
                onIntent = { viewModel.onIntent(it) }
            )
        }
}