package com.ilizma.work.view.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.ilizma.resources.ui.theme.CurriculumVitaeAppTheme
import com.ilizma.work.presentation.model.Work
import com.ilizma.work.presentation.model.WorkIntent
import com.ilizma.work.presentation.model.WorkScreenNavigationAction
import com.ilizma.work.presentation.model.WorkState
import com.ilizma.work.presentation.viewmodel.WorkScreenViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
@PreviewLightDark
private fun WorkScreenPreview(
    @PreviewParameter(RadioScreenPreviewProvider::class) viewModel: WorkScreenViewModel,
    paddingValues: PaddingValues = PaddingValues(),
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
) {
    CurriculumVitaeAppTheme(dynamicColor = false) {
        WorkScreen(
            viewModel = viewModel,
            paddingValues = paddingValues,
            snackbarHostState = snackbarHostState,
        )
    }
}

private class RadioScreenPreviewProvider : PreviewParameterProvider<WorkScreenViewModel> {
    override val values: Sequence<WorkScreenViewModel> = sequenceOf(
        FakeViewModel(WorkState.Loading),
        FakeViewModel(WorkState.Error("Fake error message")),
        FakeViewModel(
            Work(
                title = "Fake title",
                task = "Fake task",
                company = "Fake company",
                place = "Fake place",
                startDate = "Fake start date",
                endDate = "Fake end date",
                currently = true,
            ).let { persistentListOf(it) }
                .let { WorkState.Success(it) }
        ),
    )

    class FakeViewModel(
        state: WorkState,
    ) : WorkScreenViewModel() {

        override val navigationAction: Flow<WorkScreenNavigationAction>
            get() = TODO("Fake VM")

        override val workState: Flow<WorkState> = flowOf(state)

        override fun onIntent(intent: WorkIntent) {
        }

    }
}