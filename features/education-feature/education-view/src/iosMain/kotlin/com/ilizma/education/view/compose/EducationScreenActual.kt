package com.ilizma.education.view.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import com.ilizma.education.presentation.model.EducationState
import com.ilizma.education.presentation.viewmodel.EducationScreenViewModel
import com.ilizma.view.lifecycle.collectAsStateMultiplatform

@Composable
actual fun EducationScreen(
    viewModel: EducationScreenViewModel,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
) {
    viewModel.educationState
        .collectAsStateMultiplatform(
            initialValue = EducationState.Loading,
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