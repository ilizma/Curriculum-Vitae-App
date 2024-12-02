package com.ilizma.curriculum.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable

data class PersonalDataDTO(
    @SerialName(value = "photo") val photo: String? = null,
    @SerialName(value = "name") val name: String? = null,
    @SerialName(value = "surname") val surname: String? = null,
    @SerialName(value = "surname_2") val surname_2: String? = null,
    @SerialName(value = "phone") val phone: String? = null,
    @SerialName(value = "email") val email: String? = null,
    @SerialName(value = "address") val address: String? = null,
    @SerialName(value = "city") val city: String? = null,
    @SerialName(value = "postal_code") val postal_code: String? = null,
    @SerialName(value = "born_date") val born_date: String? = null,
)