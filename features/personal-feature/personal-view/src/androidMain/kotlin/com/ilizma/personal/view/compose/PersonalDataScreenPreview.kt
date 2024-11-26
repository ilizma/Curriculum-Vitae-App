package com.ilizma.personal.view.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.ilizma.personal.presentation.model.Other
import com.ilizma.personal.presentation.model.PersonalDataIntent
import com.ilizma.personal.presentation.model.PersonalDataScreenNavigationAction
import com.ilizma.personal.presentation.model.PersonalDataState
import com.ilizma.personal.presentation.viewmodel.PersonalDataScreenViewModel
import com.ilizma.resources.ui.theme.CurriculumVitaeAppTheme
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
@PreviewLightDark
private fun PersonalDataScreenPreview(
    @PreviewParameter(RadioScreenPreviewProvider::class) viewModel: PersonalDataScreenViewModel,
    paddingValues: PaddingValues = PaddingValues(),
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
) {
    CurriculumVitaeAppTheme(dynamicColor = false) {
        PersonalDataScreen(
            viewModel = viewModel,
            paddingValues = paddingValues,
            snackbarHostState = snackbarHostState,
        )
    }
}

private class RadioScreenPreviewProvider : PreviewParameterProvider<PersonalDataScreenViewModel> {
    override val values: Sequence<PersonalDataScreenViewModel> = sequenceOf(
        FakeViewModel(PersonalDataState.Loading),
        FakeViewModel(PersonalDataState.Error("Fake error message")),
        FakeViewModel(
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
            )
        ),
    )

    class FakeViewModel(
        state: PersonalDataState,
    ) : PersonalDataScreenViewModel() {

        override val navigationAction: Flow<PersonalDataScreenNavigationAction>
            get() = TODO("Fake VM")

        override val personalDataState: Flow<PersonalDataState> = flowOf(state)

        override fun onIntent(intent: PersonalDataIntent) {
        }

    }
}