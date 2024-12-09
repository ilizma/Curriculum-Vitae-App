package com.ilizma.personal.view.compose

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
import com.ilizma.personal.presentation.model.Other
import com.ilizma.personal.presentation.model.PersonalDataIntent
import com.ilizma.personal.presentation.model.PersonalDataState
import com.ilizma.personal.presentation.viewmodel.PersonalDataScreenViewModel
import com.ilizma.resources.ui.theme.CurriculumVitaeAppTheme
import com.ilizma.view.lifecycle.collectAsStateMultiplatform
import kotlinx.collections.immutable.persistentListOf

@Composable
actual fun PersonalDataScreen(
    viewModel: PersonalDataScreenViewModel,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
) {
    BackHandler { viewModel.onIntent(PersonalDataIntent.Back) }

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

@Composable
@PreviewLightDark
private fun PersonalDataScreenPreview(
    @PreviewParameter(RadioScreenPreviewProvider::class) personalDataState: PersonalDataState,
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
) {
    CurriculumVitaeAppTheme(dynamicColor = false) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            snackbarHost = { SnackbarHost(snackbarHostState) },
        ) { paddingValues ->
            ScreenState(
                state = personalDataState,
                paddingValues = paddingValues,
                snackbarHostState = snackbarHostState,
                onIntent = { },
            )
        }

    }
}

private class RadioScreenPreviewProvider : PreviewParameterProvider<PersonalDataState> {
    override val values: Sequence<PersonalDataState> = sequenceOf(
        PersonalDataState.Loading,
        PersonalDataState.Error("Fake error message"),

        PersonalDataState.Success(
            photo = "Fake image",
            name = "Fake name",
            surname = "Fake surname",
            surname2 = "Fake surname2",
            phone = "Fake phone",
            email = "Fake email",
            address = "Fake address",
            city = "Fake city",
            postalCode = "Fake postalCode",
            bornDate = "Fake bornDate",
            description = "Fake description",
            skills = persistentListOf("Fake skills"),
            other = persistentListOf(
                Other(
                    title = "Fake title",
                    description = "Fake description",
                    link = "Fake link"
                ),
            ),
        ),
    )
}