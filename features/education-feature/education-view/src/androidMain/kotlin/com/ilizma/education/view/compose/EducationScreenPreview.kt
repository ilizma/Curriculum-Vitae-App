package com.ilizma.education.view.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.ilizma.education.presentation.model.ComplementaryEducation
import com.ilizma.education.presentation.model.Education
import com.ilizma.education.presentation.model.EducationIntent
import com.ilizma.education.presentation.model.EducationScreenNavigationAction
import com.ilizma.education.presentation.model.EducationState
import com.ilizma.education.presentation.viewmodel.EducationScreenViewModel
import com.ilizma.resources.ui.theme.CurriculumVitaeAppTheme
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
@PreviewLightDark
private fun EducationScreenPreview(
    @PreviewParameter(RadioScreenPreviewProvider::class) viewModel: EducationScreenViewModel,
    paddingValues: PaddingValues = PaddingValues(),
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
) {
    CurriculumVitaeAppTheme(dynamicColor = false) {
        EducationScreen(
            viewModel = viewModel,
            paddingValues = paddingValues,
            snackbarHostState = snackbarHostState,
        )
    }
}

private class RadioScreenPreviewProvider : PreviewParameterProvider<EducationScreenViewModel> {
    override val values: Sequence<EducationScreenViewModel> = sequenceOf(
        FakeViewModel(EducationState.Loading),
        FakeViewModel(EducationState.Error("Fake error message")),
        FakeViewModel(
            (Education(
                title = "Fake title",
                place = "Fake place",
                startDate = "Fake start date",
                endDate = "Fake end date",
                currently = true,
            ).let { persistentListOf(it) } to ComplementaryEducation(
                type = "Fake type",
                title = "Fake title",
                hours = "Fake hours",
                date = "Fake date",
                place = "Fake place",
            ).let { persistentListOf(it) })
                .let { (education, complementaryEducation) ->
                    EducationState.Success(
                        education,
                        complementaryEducation
                    )
                }
        ),
    )

    class FakeViewModel(
        state: EducationState,
    ) : EducationScreenViewModel() {

        override val navigationAction: Flow<EducationScreenNavigationAction>
            get() = TODO("Fake VM")

        override val educationState: Flow<EducationState> = flowOf(state)

        override fun onIntent(intent: EducationIntent) {
        }

    }
}