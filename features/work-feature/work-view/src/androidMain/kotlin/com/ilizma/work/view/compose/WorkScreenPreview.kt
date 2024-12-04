package com.ilizma.work.view.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.ilizma.resources.ui.theme.CurriculumVitaeAppTheme
import com.ilizma.work.presentation.model.Work
import com.ilizma.work.presentation.model.WorkState
import kotlinx.collections.immutable.persistentListOf

@Composable
@PreviewLightDark
private fun WorkScreenPreview(
    @PreviewParameter(RadioScreenPreviewProvider::class) workState: WorkState,
    paddingValues: PaddingValues = PaddingValues(),
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
) {
    CurriculumVitaeAppTheme(dynamicColor = false) {
        ScreenState(
            state = workState,
            paddingValues = paddingValues,
            snackbarHostState = snackbarHostState,
            onIntent = {},
        )
    }
}

private class RadioScreenPreviewProvider : PreviewParameterProvider<WorkState> {
    override val values: Sequence<WorkState> = sequenceOf(
        WorkState.Loading,
        WorkState.Error("Fake error message"),
        Work(
            title = "Fake title",
            task = "Fake task",
            company = "Fake company",
            place = "Fake place",
            startDate = "Fake start date",
            endDate = "Fake end date",
            currently = true,
        ).let { persistentListOf(it) }
            .let { WorkState.Success(it) },
    )
}