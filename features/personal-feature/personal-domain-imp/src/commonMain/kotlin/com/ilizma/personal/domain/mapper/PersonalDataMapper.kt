package com.ilizma.personal.domain.mapper

import com.ilizma.curriculum.domain.model.CurriculumVitae
import com.ilizma.personal.domain.model.PersonalData

class PersonalDataMapper {

    fun from(
        state: CurriculumVitae
    ): PersonalData = state.personalData.let {
        PersonalData(
            photo = it.photo,
            name = it.name,
            surname = it.surname,
            surname2 = it.surname2,
            phone = it.phone,
            email = it.email,
            address = it.address,
            city = it.city,
            postalCode = it.postalCode,
            bornDate = it.bornDate,
        )
    }
}