package com.ilizma.curriculum.data.maper

import com.ilizma.curriculum.data.mapper.PersonalDataMapper
import com.ilizma.curriculum.data.model.PersonalDataDTO
import com.ilizma.curriculum.domain.model.PersonalData
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import com.ilizma.curriculum.data.model.PersonalData as DataPersonalData

class PersonalDataMapperTest {

    private lateinit var mapper: PersonalDataMapper

    @BeforeTest
    fun setup() {
        mapper = PersonalDataMapper()
    }

    @Test
    fun `given PersonalDataDTO, when from, then result should be the expected DataPersonalData`() {
        // given
        val data = PersonalDataDTO(
            photo = "photo",
            name = "name",
            surname = "surname",
            surname_2 = "surname_2",
            phone = "phone",
            email = "email",
            address = "address",
            city = "city",
            postal_code = "postal_code",
            born_date = "born_date",
        )
        val expected = DataPersonalData(
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

    @Test
    fun `given DataPersonalData, when from, then result should be the expected PersonalData`() {
        // given
        val data = DataPersonalData(
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