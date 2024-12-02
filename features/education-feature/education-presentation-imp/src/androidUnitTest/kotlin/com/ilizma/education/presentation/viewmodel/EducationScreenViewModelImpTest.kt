package com.ilizma.education.presentation.viewmodel

import com.ilizma.education.domain.model.ComplementaryEducation
import com.ilizma.education.domain.model.Education
import com.ilizma.education.domain.usecase.ComplementaryEducationUseCase
import com.ilizma.education.domain.usecase.EducationUseCase
import com.ilizma.education.presentation.mapper.EducationMapper
import com.ilizma.education.presentation.model.EducationIntent
import com.ilizma.education.presentation.model.EducationScreenNavigationAction.Back
import com.ilizma.education.presentation.model.EducationState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class EducationScreenViewModelImpTest {

    @RelaxedMockK
    private lateinit var educationUseCase: EducationUseCase

    @RelaxedMockK
    private lateinit var complementaryEducationUseCase: ComplementaryEducationUseCase

    @RelaxedMockK
    private lateinit var mapper: EducationMapper

    @RelaxedMockK
    private lateinit var education: Education

    @RelaxedMockK
    private lateinit var complementaryEducation: ComplementaryEducation

    @RelaxedMockK
    private lateinit var successEducationState: EducationState.Success

    private lateinit var viewModel: EducationScreenViewModel

    init {
        MockKAnnotations.init(this)

        coEvery { educationUseCase.invoke() } returns listOf(education)
        coEvery { complementaryEducationUseCase.invoke() } returns listOf(complementaryEducation)
        every {
            mapper.from(
                educationList = listOf(education),
                complementaryEducationList = listOf(complementaryEducation),
            )
        } returns successEducationState
    }

    private fun setup(standardTestDispatcher: TestDispatcher) {
        viewModel = EducationScreenViewModelImp(
            dispatcher = standardTestDispatcher,
            educationUseCase = educationUseCase,
            complementaryEducationUseCase = complementaryEducationUseCase,
            mapper = mapper,
            isDebug = true,
            _educationState = MutableStateFlow(EducationState.Loading),
            _navigationAction = MutableSharedFlow(),
        )
    }

    @Test
    fun `when init, then result should be the expected successEducationState`() =
        runTest {
            // given
            val standardTestDispatcher = StandardTestDispatcher(testScheduler)
            val expected = successEducationState

            // when
            setup(standardTestDispatcher)

            backgroundScope.launch(standardTestDispatcher) {
                // then
                assertEquals(expected, viewModel.educationState.last())
            }

        }

    @Test
    fun `given Retry EducationIntent, when onIntent, then result should be the expected successEducationState`() =
        runTest {
            // given
            val standardTestDispatcher = StandardTestDispatcher(testScheduler)
            setup(standardTestDispatcher)
            val expected = successEducationState

            backgroundScope.launch(standardTestDispatcher) {
                // when
                viewModel.onIntent(EducationIntent.Retry)

                // then
                assertEquals(expected, viewModel.educationState.last())
            }

        }

    @Test
    fun `given Back EducationIntent, when onIntent, then result should be the expected Back`() =
        runTest {
            // given
            val standardTestDispatcher = StandardTestDispatcher(testScheduler)
            setup(standardTestDispatcher)
            val expected = Back

            // when
            viewModel.onIntent(EducationIntent.Back)

            // then
            assertEquals(expected, viewModel.navigationAction.first())
        }

}