package com.ilizma.personal.view.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import com.ilizma.personal.presentation.model.Other
import com.ilizma.personal.presentation.model.PersonalDataState
import com.ilizma.personal.view.utils.ERROR_TAG
import com.ilizma.personal.view.utils.LOADING_TAG
import com.ilizma.personal.view.utils.PERSONAL_ADDRESS_TAG
import com.ilizma.personal.view.utils.PERSONAL_BORN_DATE_TAG
import com.ilizma.personal.view.utils.PERSONAL_CITY_TAG
import com.ilizma.personal.view.utils.PERSONAL_DESCRIPTION_TAG
import com.ilizma.personal.view.utils.PERSONAL_EMAIL_TAG
import com.ilizma.personal.view.utils.PERSONAL_IMAGE_TAG
import com.ilizma.personal.view.utils.PERSONAL_NAME_TAG
import com.ilizma.personal.view.utils.PERSONAL_OTHER_DESCRIPTION_TAG
import com.ilizma.personal.view.utils.PERSONAL_OTHER_LINK_TAG
import com.ilizma.personal.view.utils.PERSONAL_OTHER_TITLE_TAG
import com.ilizma.personal.view.utils.PERSONAL_PHONE_TAG
import com.ilizma.personal.view.utils.PERSONAL_POSTAL_CODE_TAG
import com.ilizma.personal.view.utils.PERSONAL_SKILLS_TAG
import com.ilizma.personal.view.utils.PERSONAL_SURNAME2_TAG
import com.ilizma.personal.view.utils.PERSONAL_SURNAME_TAG
import com.ilizma.resources.ui.theme.CurriculumVitaeAppTheme
import kotlinx.collections.immutable.persistentListOf
import kotlin.test.Test

class PersonalScreenAndroidTest {

    @OptIn(ExperimentalTestApi::class)
    private fun ComposeUiTest.initialize(state: PersonalDataState) {
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
    fun testLoadingPersonalScreen() = runComposeUiTest {
        // given
        val state = PersonalDataState.Loading
        // when
        initialize(
            state = state,
        )

        // then
        onNodeWithTag(LOADING_TAG).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testSuccessPersonalScreen() = runComposeUiTest {
        // given
        val state = PersonalDataState.Success(
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

        // when
        initialize(
            state = state,
        )

        // then
        onNodeWithTag(PERSONAL_IMAGE_TAG).assertIsDisplayed()
        onNodeWithTag(PERSONAL_NAME_TAG).assertIsDisplayed()
        onNodeWithTag(PERSONAL_SURNAME_TAG).assertIsDisplayed()
        onNodeWithTag(PERSONAL_SURNAME2_TAG).assertIsDisplayed()
        onNodeWithTag(PERSONAL_PHONE_TAG).assertIsDisplayed()
        onNodeWithTag(PERSONAL_EMAIL_TAG).assertIsDisplayed()
        onNodeWithTag(PERSONAL_ADDRESS_TAG).assertIsDisplayed()
        onNodeWithTag(PERSONAL_CITY_TAG).assertIsDisplayed()
        onNodeWithTag(PERSONAL_POSTAL_CODE_TAG).assertIsDisplayed()
        onNodeWithTag(PERSONAL_BORN_DATE_TAG).assertIsDisplayed()
        onNodeWithTag(PERSONAL_SKILLS_TAG).assertIsDisplayed()
        onNodeWithTag(PERSONAL_DESCRIPTION_TAG).assertIsDisplayed()
        onNodeWithTag(PERSONAL_OTHER_TITLE_TAG).assertIsDisplayed()
        onNodeWithTag(PERSONAL_OTHER_DESCRIPTION_TAG).assertIsDisplayed()
        onNodeWithTag(PERSONAL_OTHER_LINK_TAG).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testErrorPersonalScreen() = runComposeUiTest {
        // given
        val errorMessage = "errorMessage"
        val state = PersonalDataState.Error(
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