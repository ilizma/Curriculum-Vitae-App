package com.ilizma.curriculum.data.mapper

import com.ilizma.curriculum.data.model.PersonalDataDTO
import com.ilizma.curriculum.domain.model.PersonalData
import com.ilizma.curriculum.data.model.PersonalData as DataPersonalData

class PersonalDataMapper {

    fun from(
        data: PersonalDataDTO,
    ): DataPersonalData = DataPersonalData(
        photo = data.photo ?: "",
        name = data.name ?: throw nullParameterException("personalData.name"),
        surname = data.surname ?: throw nullParameterException("personalData.surname"),
        surname2 = data.surname_2 ?: "",
        phone = data.phone ?: "",
        email = data.email ?: "",
        address = data.address ?: "",
        city = data.city ?: "",
        postalCode = data.postal_code ?: "",
        bornDate = data.born_date ?: "",
    )

    fun from(
        data: DataPersonalData,
    ): PersonalData = PersonalData(
        photo = data.photo,
        name = data.name,
        surname = data.surname,
        surname2 = data.surname2,
        phone = data.phone,
        email = data.email,
        address = data.address,
        city = data.city,
        postalCode = data.postalCode,
        bornDate = data.bornDate,
    )

    private fun nullParameterException(
        parameter: String
    ): IllegalArgumentException = IllegalArgumentException("$parameter can not be null")

}