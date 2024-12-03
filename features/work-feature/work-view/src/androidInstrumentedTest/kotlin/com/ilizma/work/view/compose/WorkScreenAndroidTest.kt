package com.ilizma.work.view.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import com.ilizma.resources.ui.theme.CurriculumVitaeAppTheme
import com.ilizma.work.presentation.model.Work
import com.ilizma.work.presentation.model.WorkState
import com.ilizma.work.view.utils.ERROR_TAG
import com.ilizma.work.view.utils.LOADING_TAG
import com.ilizma.work.view.utils.WORK_COMPANY_TAG
import com.ilizma.work.view.utils.WORK_DATE_TAG
import com.ilizma.work.view.utils.WORK_PLACE_TAG
import com.ilizma.work.view.utils.WORK_TASK_TAG
import com.ilizma.work.view.utils.WORK_TITLE_TAG
import kotlinx.collections.immutable.persistentListOf
import kotlin.test.Test

class WorkScreenAndroidTest {

    @OptIn(ExperimentalTestApi::class)
    private fun ComposeUiTest.initialize(state: WorkState) {
        setContent {
            CurriculumVitaeAppTheme(dynamicColor = false) {
                ScreenState(
                    state = state,
                    paddingValues = PaddingValues(),
                    snackbarHostState = remember { SnackbarHostState() },
                    onIntent = {},
                )
            }
        }
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testLoadingWorkScreen() = runComposeUiTest {
        // given
        val state = WorkState.Loading
        // when
        initialize(
            state = state,
        )

        // then
        onNodeWithTag(LOADING_TAG).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testSuccessWorkScreen() = runComposeUiTest {
        // given
        val state = WorkState.Success(
            list = Work(
                title = "title",
                task = "task",
                company = "company",
                place = "place",
                startDate = "start_date",
                endDate = "end_date",
                currently = false,
            ).let { persistentListOf(it) },
        )

        // when
        initialize(
            state = state,
        )

        // then
        onNodeWithTag(WORK_TITLE_TAG).assertIsDisplayed()
        onNodeWithTag(WORK_TASK_TAG).assertIsDisplayed()
        onNodeWithTag(WORK_COMPANY_TAG).assertIsDisplayed()
        onNodeWithTag(WORK_PLACE_TAG).assertIsDisplayed()
        onNodeWithTag(WORK_DATE_TAG).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testErrorWorkScreen() = runComposeUiTest {
        // given
        val errorMessage = "errorMessage"
        val state = WorkState.Error(
            message = errorMessage,
        )

        // when
        initialize(
            state = state,
        )

        // then
        onNodeWithTag(ERROR_TAG).assertIsDisplayed()
        onNodeWithText(errorMessage).assertIsDisplayed()
    }

}