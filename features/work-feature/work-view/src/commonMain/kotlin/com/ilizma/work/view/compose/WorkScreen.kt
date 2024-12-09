package com.ilizma.work.view.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ilizma.resources.Res
import com.ilizma.resources.end_date_currently
import com.ilizma.resources.retry
import com.ilizma.work.presentation.model.WorkIntent
import com.ilizma.work.presentation.model.WorkState
import com.ilizma.work.presentation.viewmodel.WorkScreenViewModel
import com.ilizma.work.view.utils.WORK_COMPANY_TAG
import com.ilizma.work.view.utils.WORK_DATE_TAG
import com.ilizma.work.view.utils.WORK_PLACE_TAG
import com.ilizma.work.view.utils.WORK_TASK_TAG
import com.ilizma.work.view.utils.WORK_TITLE_TAG
import org.jetbrains.compose.resources.stringResource

@Composable
expect fun WorkScreen(
    viewModel: WorkScreenViewModel,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
)

@Composable
internal fun ScreenState(
    state: WorkState,
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues,
    onIntent: (WorkIntent) -> Unit,
) {
    when (state) {
        is WorkState.Success -> Content(
            paddingValues = paddingValues,
            state = state,
        )

        is WorkState.Error -> ErrorSnackbar(
            paddingValues = paddingValues,
            state = state,
            snackbarHostState = snackbarHostState,
            onRetry = { onIntent(WorkIntent.Retry) }
        )

        WorkState.Loading -> Loading(paddingValues)
    }

}

@Composable
private fun Content(
    paddingValues: PaddingValues,
    state: WorkState.Success,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        itemsIndexed(
            items = state.list,
            key = { _, it -> it.title + it.company },
        ) { index, education ->
            Text(
                modifier = Modifier.fillMaxWidth()
                    .testTag(WORK_TITLE_TAG),
                text = education.title,
                fontWeight = FontWeight.Bold,
            )
            if (education.task.isNotBlank()) Text(
                modifier = Modifier.fillMaxWidth()
                    .testTag(WORK_TASK_TAG),
                text = education.task,
                fontStyle = FontStyle.Italic,
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.testTag(WORK_COMPANY_TAG),
                    text = education.company
                )
                Text(text = " - ")
                Text(
                    modifier = Modifier.testTag(WORK_PLACE_TAG),
                    text = education.place
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
                    .testTag(WORK_DATE_TAG)
            ) {
                Text(text = education.startDate)
                Text(text = " - ")
                Text(text = if (education.currently) stringResource(Res.string.end_date_currently) else education.endDate)
            }
            if (index != state.list.lastIndex) HorizontalDivider()
        }
    }
}

@Composable
private fun ErrorSnackbar(
    paddingValues: PaddingValues,
    state: WorkState.Error,
    snackbarHostState: SnackbarHostState,
    onRetry: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(paddingValues),
    ) {
        val message = state.message
        val actionLabel = stringResource(Res.string.retry)
        LaunchedEffect(snackbarHostState) {
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel,
            ).let { snackbarResult ->
                if (snackbarResult == SnackbarResult.ActionPerformed) {
                    onRetry()
                }
            }
        }
    }
}

@Composable
private fun Loading(paddingValues: PaddingValues) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
            .padding(paddingValues),
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}
