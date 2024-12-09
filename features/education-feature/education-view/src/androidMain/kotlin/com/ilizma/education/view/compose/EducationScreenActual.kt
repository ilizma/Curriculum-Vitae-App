package com.ilizma.education.view.compose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.ilizma.education.presentation.model.ComplementaryEducation
import com.ilizma.education.presentation.model.Education
import com.ilizma.education.presentation.model.EducationIntent
import com.ilizma.education.presentation.model.EducationState
import com.ilizma.education.presentation.viewmodel.EducationScreenViewModel
import com.ilizma.resources.ui.theme.CurriculumVitaeAppTheme
import com.ilizma.view.lifecycle.collectAsStateMultiplatform
import kotlinx.collections.immutable.persistentListOf

@Composable
actual fun EducationScreen(
    viewModel: EducationScreenViewModel,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
) {
    BackHandler { viewModel.onIntent(EducationIntent.Back) }

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

@Composable
@PreviewLightDark
private fun EducationScreenPreview(
    @PreviewParameter(RadioScreenPreviewProvider::class) educationState: EducationState,
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
) {
    CurriculumVitaeAppTheme(dynamicColor = false) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            snackbarHost = { SnackbarHost(snackbarHostState) },
        ) { paddingValues ->
            ScreenState(
                state = educationState,
                paddingValues = paddingValues,
                snackbarHostState = snackbarHostState,
                onIntent = {},
            )
        }
    }
}

private class RadioScreenPreviewProvider : PreviewParameterProvider<EducationState> {
    override val values: Sequence<EducationState> = sequenceOf(
        EducationState.Loading,
        EducationState.Error("Fake error message"),
        (Education(
            title = "Fake title1",
            place = "Fake place",
            startDate = "Fake start date",
            endDate = "Fake end date",
            currently = true,
        ).let { persistentListOf(it) } to ComplementaryEducation(
            type = "Fake type",
            title = "Fake title2",
            hours = "Fake ours",
            date = "Fake date",
            place = "Fake place",
        ).let { persistentListOf(it) })
            .let { (education, complementaryEducation) ->
                EducationState.Success(
                    education = education,
                    complementaryEducation = complementaryEducation
                )
            },
    )
}