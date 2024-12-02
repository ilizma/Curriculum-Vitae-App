package com.ilizma.personal.domain.mapper

import com.ilizma.personal.domain.model.PersonalData
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import com.ilizma.curriculum.domain.model.PersonalData as CVPersonalData

class PersonalDataMapperTest {

    private lateinit var mapper: PersonalDataMapper

    @BeforeTest
    fun setup() {
        mapper = PersonalDataMapper()
    }

    @Test
    fun `given CVPersonalData, when from, then result should be the expected PersonalData`() {
        // given
        val data = CVPersonalData(
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
        val expected = PersonalData(
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

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

}