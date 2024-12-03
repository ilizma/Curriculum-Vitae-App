package com.ilizma.education.view.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilizma.education.presentation.model.EducationIntent
import com.ilizma.education.presentation.model.EducationState
import com.ilizma.education.presentation.viewmodel.EducationScreenViewModel
import com.ilizma.education.view.utils.COMPLEMENTARY_EDUCATION_DATE_TAG
import com.ilizma.education.view.utils.COMPLEMENTARY_EDUCATION_HOURS_TAG
import com.ilizma.education.view.utils.COMPLEMENTARY_EDUCATION_PLACE_TAG
import com.ilizma.education.view.utils.COMPLEMENTARY_EDUCATION_SECTION_TITLE_TAG
import com.ilizma.education.view.utils.COMPLEMENTARY_EDUCATION_TITLE_TAG
import com.ilizma.education.view.utils.COMPLEMENTARY_EDUCATION_TYPE_TAG
import com.ilizma.education.view.utils.EDUCATION_DATE_TAG
import com.ilizma.education.view.utils.EDUCATION_PLACE_TAG
import com.ilizma.education.view.utils.EDUCATION_TITLE_TAG
import com.ilizma.education.view.utils.ERROR_TAG
import com.ilizma.education.view.utils.LOADING_TAG
import com.ilizma.resources.Res
import com.ilizma.resources.end_date_currently
import com.ilizma.resources.retry
import com.ilizma.resources.title_complementary_education
import com.ilizma.view.lifecycle.collectAsStateMultiplatform
import org.jetbrains.compose.resources.stringResource

@Composable
fun EducationScreen(
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

@Composable
internal fun ScreenState(
    state: EducationState,
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues,
    onIntent: (EducationIntent) -> Unit
) {
    when (state) {
        is EducationState.Success -> Content(
            paddingValues = paddingValues,
            state = state,
        )

        is EducationState.Error -> ErrorSnackbar(
            paddingValues = paddingValues,
            state = state,
            snackbarHostState = snackbarHostState,
            onRetry = { onIntent(EducationIntent.Retry) },
        )

        EducationState.Loading -> Loading(paddingValues)
    }

}

@Composable
private fun Content(
    paddingValues: PaddingValues,
    state: EducationState.Success,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        itemsIndexed(
            items = state.education,
            key = { _, it -> it.title },
        ) { index, education ->
            Text(
                modifier = Modifier.fillMaxWidth()
                    .testTag(EDUCATION_TITLE_TAG),
                text = education.title,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.fillMaxWidth()
                    .testTag(EDUCATION_PLACE_TAG),
                text = education.place
            )
            Row(
                modifier = Modifier.fillMaxWidth()
                    .testTag(EDUCATION_DATE_TAG)
            ) {
                Text(text = education.startDate)
                Text(text = " - ")
                Text(text = if (education.currently) stringResource(Res.string.end_date_currently) else education.endDate)
            }
            if (index != state.education.lastIndex) HorizontalDivider()
        }

        item {
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                modifier = Modifier.fillMaxWidth()
                    .testTag(COMPLEMENTARY_EDUCATION_SECTION_TITLE_TAG),
                text = stringResource(Res.string.title_complementary_education),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
            Spacer(modifier = Modifier.padding(2.dp))
        }

        itemsIndexed(
            items = state.complementaryEducation,
            key = { _, it -> it.title },
        ) { index, complementaryEducation ->
            Text(
                modifier = Modifier.fillMaxWidth()
                    .testTag(COMPLEMENTARY_EDUCATION_TITLE_TAG),
                text = complementaryEducation.title,
                fontWeight = FontWeight.Bold
            )
            if (complementaryEducation.type.isNotBlank()) Text(
                modifier = Modifier.fillMaxWidth()
                    .testTag(COMPLEMENTARY_EDUCATION_TYPE_TAG),
                text = complementaryEducation.type
            )
            if (complementaryEducation.hours.isNotBlank()) Text(
                modifier = Modifier.fillMaxWidth()
                    .testTag(COMPLEMENTARY_EDUCATION_HOURS_TAG),
                text = complementaryEducation.hours
            )
            Text(
                modifier = Modifier.fillMaxWidth()
                    .testTag(COMPLEMENTARY_EDUCATION_PLACE_TAG),
                text = complementaryEducation.place
            )
            Text(
                modifier = Modifier.fillMaxWidth()
                    .testTag(COMPLEMENTARY_EDUCATION_DATE_TAG),
                text = complementaryEducation.date
            )
            if (index != state.complementaryEducation.lastIndex) HorizontalDivider()
        }
    }
}

@Composable
private fun ErrorSnackbar(
    paddingValues: PaddingValues,
    state: EducationState.Error,
    snackbarHostState: SnackbarHostState,
    onRetry: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(paddingValues)
            .testTag(ERROR_TAG),
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
            modifier = Modifier.testTag(LOADING_TAG),
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}
