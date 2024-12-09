package com.ilizma.work.view.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import com.ilizma.view.lifecycle.collectAsStateMultiplatform
import com.ilizma.work.presentation.model.WorkState
import com.ilizma.work.presentation.viewmodel.WorkScreenViewModel

@Composable
actual fun WorkScreen(
    viewModel: WorkScreenViewModel,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
) {
    viewModel.workState
        .collectAsStateMultiplatform(
            initialValue = WorkState.Loading,
        ).value
        .let {
            ScreenState(
                state = it,
                snackbarHostState = snackbarHostState,
                paddingValues = paddingValues,
                onIntent = { viewModel.onIntent(it) }
            )
        }
}