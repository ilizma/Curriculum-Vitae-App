package com.ilizma.personal.presentation.viewmodel

import com.ilizma.personal.domain.model.Other
import com.ilizma.personal.domain.model.PersonalData
import com.ilizma.personal.domain.usecase.DescriptionUseCase
import com.ilizma.personal.domain.usecase.OtherUseCase
import com.ilizma.personal.domain.usecase.PersonalDataUseCase
import com.ilizma.personal.domain.usecase.SkillsUseCase
import com.ilizma.personal.presentation.mapper.PersonalDataMapper
import com.ilizma.personal.presentation.model.PersonalDataIntent
import com.ilizma.personal.presentation.model.PersonalDataScreenNavigationAction
import com.ilizma.personal.presentation.model.PersonalDataState
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

class PersonalDataScreenViewModelImpTest {

    @RelaxedMockK
    private lateinit var personalDataUseCase: PersonalDataUseCase

    @RelaxedMockK
    private lateinit var descriptionUseCase: DescriptionUseCase

    @RelaxedMockK
    private lateinit var skillsUseCase: SkillsUseCase

    @RelaxedMockK
    private lateinit var otherUseCase: OtherUseCase

    @RelaxedMockK
    private lateinit var mapper: PersonalDataMapper

    @RelaxedMockK
    private lateinit var personalData: PersonalData

    @RelaxedMockK
    private lateinit var other: Other

    @RelaxedMockK
    private lateinit var successPersonalDataState: PersonalDataState.Success

    private val description = "description"
    private val skills = listOf("skill")

    private lateinit var viewModel: PersonalDataScreenViewModel

    init {
        MockKAnnotations.init(this)

        coEvery { personalDataUseCase.invoke() } returns personalData
        coEvery { descriptionUseCase.invoke() } returns description
        coEvery { skillsUseCase.invoke() } returns skills
        coEvery { otherUseCase.invoke() } returns listOf(other)
        every {
            mapper.from(
                data = personalData,
                description = description,
                skills = skills,
                other = listOf(other)
            )
        } returns successPersonalDataState
    }

    private fun setup(standardTestDispatcher: TestDispatcher) {
        viewModel = PersonalDataScreenViewModelImp(
            dispatcher = standardTestDispatcher,
            personalDataUseCase = personalDataUseCase,
            descriptionUseCase = descriptionUseCase,
            skillsUseCase = skillsUseCase,
            otherUseCase = otherUseCase,
            mapper = mapper,
            isDebug = true,
            _personalDataState = MutableStateFlow(PersonalDataState.Loading),
            _navigationAction = MutableSharedFlow(),
        )
    }

    @Test
    fun `when init, then result should be the expected successPersonalDataState`() =
        runTest {
            // given
            val standardTestDispatcher = StandardTestDispatcher(testScheduler)
            val expected = successPersonalDataState

            // when
            setup(standardTestDispatcher)

            backgroundScope.launch(standardTestDispatcher) {
                // then
                assertEquals(expected, viewModel.personalDataState.last())
            }

        }

    @Test
    fun `given Phone PersonalDataIntent, when onIntent, then result should be the expected Phone PersonalDataScreenNavigationAction`() =
        runTest {
            // given
            val phone = "phone"
            val standardTestDispatcher = StandardTestDispatcher(testScheduler)
            setup(standardTestDispatcher)
            val expected = PersonalDataScreenNavigationAction.Phone(phone)

            backgroundScope.launch(standardTestDispatcher) {
                // when
                viewModel.onIntent(PersonalDataIntent.Phone(phone))

                // then
                assertEquals(expected, viewModel.navigationAction.last())
            }

        }

    @Test
    fun `given Email PersonalDataIntent, when onIntent, then result should be the expected Email PersonalDataScreenNavigationAction`() =
        runTest {
            // given
            val email = "email"
            val standardTestDispatcher = StandardTestDispatcher(testScheduler)
            setup(standardTestDispatcher)
            val expected = PersonalDataScreenNavigationAction.Email(email)

            backgroundScope.launch(standardTestDispatcher) {
                // when
                viewModel.onIntent(PersonalDataIntent.Email(email))

                // then
                assertEquals(expected, viewModel.navigationAction.last())
            }

        }

    @Test
    fun `given Retry PersonalDataIntent, when onIntent, then result should be the expected successPersonalDataState`() =
        runTest {
            // given
            val standardTestDispatcher = StandardTestDispatcher(testScheduler)
            setup(standardTestDispatcher)
            val expected = successPersonalDataState

            backgroundScope.launch(standardTestDispatcher) {
                // when
                viewModel.onIntent(PersonalDataIntent.Retry)

                // then
                assertEquals(expected, viewModel.personalDataState.last())
            }

        }

    @Test
    fun `given Back PersonalDataIntent, when onIntent, then result should be the expected Back`() =
        runTest {
            // given
            val standardTestDispatcher = StandardTestDispatcher(testScheduler)
            setup(standardTestDispatcher)
            val expected = PersonalDataScreenNavigationAction.Back

            // when
            viewModel.onIntent(PersonalDataIntent.Back)

            // then
            assertEquals(expected, viewModel.navigationAction.first())
        }

}