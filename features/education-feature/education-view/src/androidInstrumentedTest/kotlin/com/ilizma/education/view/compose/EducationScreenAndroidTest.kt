package com.ilizma.education.view.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import com.ilizma.education.presentation.model.ComplementaryEducation
import com.ilizma.education.presentation.model.Education
import com.ilizma.education.presentation.model.EducationState
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
import com.ilizma.resources.ui.theme.CurriculumVitaeAppTheme
import kotlinx.collections.immutable.persistentListOf
import kotlin.test.Test

class EducationScreenAndroidTest {

    @OptIn(ExperimentalTestApi::class)
    private fun ComposeUiTest.initialize(state: EducationState) {
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
    fun testLoadingEducationScreen() = runComposeUiTest {
        // given
        val state = EducationState.Loading
        // when
        initialize(
            state = state,
        )

        // then
        onNodeWithTag(LOADING_TAG).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testSuccessEducationScreen() = runComposeUiTest {
        // given
        val state = EducationState.Success(
            education = Education(
                title = "educationTitle",
                place = "place",
                startDate = "start_date",
                endDate = "end_date",
                currently = false,
            ).let { persistentListOf(it) },
            complementaryEducation = ComplementaryEducation(
                type = "complementaryEducationType",
                title = "title",
                hours = "hours",
                date = "date",
                place = "place",
            ).let { persistentListOf(it) },
        )

        // when
        initialize(
            state = state,
        )

        // then
        onNodeWithTag(EDUCATION_TITLE_TAG).assertIsDisplayed()
        onNodeWithTag(EDUCATION_PLACE_TAG).assertIsDisplayed()
        onNodeWithTag(EDUCATION_DATE_TAG).assertIsDisplayed()
        onNodeWithTag(COMPLEMENTARY_EDUCATION_SECTION_TITLE_TAG).assertIsDisplayed()
        onNodeWithTag(COMPLEMENTARY_EDUCATION_TITLE_TAG).assertIsDisplayed()
        onNodeWithTag(COMPLEMENTARY_EDUCATION_TYPE_TAG).assertIsDisplayed()
        onNodeWithTag(COMPLEMENTARY_EDUCATION_HOURS_TAG).assertIsDisplayed()
        onNodeWithTag(COMPLEMENTARY_EDUCATION_PLACE_TAG).assertIsDisplayed()
        onNodeWithTag(COMPLEMENTARY_EDUCATION_DATE_TAG).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testErrorEducationScreen() = runComposeUiTest {
        // given
        val errorMessage = "errorMessage"
        val state = EducationState.Error(
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