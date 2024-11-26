package com.ilizma.personal.presentation.mapper

import com.ilizma.personal.domain.model.Other
import com.ilizma.personal.domain.model.PersonalData
import com.ilizma.personal.presentation.model.PersonalDataState
import kotlinx.collections.immutable.toImmutableList
import com.ilizma.personal.presentation.model.Other as PresentationOther

class PersonalDataMapper {

    fun from(
        data: PersonalData,
        description: String,
        skills: List<String>,
        other: List<Other>,
    ): PersonalDataState.Success = PersonalDataState.Success(
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
        description = description,
        skills = skills.toImmutableList(),
        other = other.map {
            PresentationOther(
                title = it.title,
                description = it.description,
                link = it.link,
            )
        }.toImmutableList(),
    )

}