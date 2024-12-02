package com.ilizma.personal.presentation.mapper

import com.ilizma.personal.domain.model.Other
import com.ilizma.personal.domain.model.PersonalData
import com.ilizma.personal.presentation.model.PersonalDataState
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import com.ilizma.personal.presentation.model.Other as PresentationOther

class PersonalDataMapperTest {

    private lateinit var mapper: PersonalDataMapper

    @BeforeTest
    fun setup() {
        mapper = PersonalDataMapper()
    }

    @Test
    fun `given PersonalData, description, skills and other, when from, then result should be the expected Success PersonalDataState`() {
        // given
        val data = PersonalData(
            photo = "photo",
            name = "name",
            surname = "surname",
            surname2 = "surname_2",
            phone = "phone",
            email = "email",
            address = "address",
            city = "city",
            postalCode = "postal_code",
            bornDate = "born_date",
        )
        val description = "description"
        val skills = listOf("skill")
        val other = listOf(
            Other(
                title = "title",
                description = "description",
                link = "link",
            )
        )
        val presentationOther = persistentListOf(
            PresentationOther(
                title = "title",
                description = "description",
                link = "link",
            )
        )
        val expected = PersonalDataState.Success(
            photo = "photo",
            name = "name",
            surname = "surname",
            surname2 = "surname_2",
            phone = "phone",
            email = "email",
            address = "address",
            city = "city",
            postalCode = "postal_code",
            bornDate = "born_date",
            description = description,
            skills = skills.toImmutableList(),
            other = presentationOther,
        )

        // when
        val result = mapper.from(
            data = data,
            description = description,
            skills = skills,
            other = other,
        )

        // then
        assertEquals(expected, result)
    }

}