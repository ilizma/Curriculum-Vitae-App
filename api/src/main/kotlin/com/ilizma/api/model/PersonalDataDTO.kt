package com.ilizma.api.model

import com.squareup.moshi.Json

data class PersonalDataDTO(
    @Json(name = "name") val name: String? = null,
    @Json(name = "surname") val surname: String? = null,
    @Json(name = "surname2") val surname2: String? = null,
    @Json(name = "phone") val phone: String? = null,
    @Json(name = "email") val email: String? = null,
    @Json(name = "address") val address: String? = null,
    @Json(name = "city") val city: String? = null,
    @Json(name = "postalCode") val postalCode: String? = null,
    @Json(name = "bornDate") val bornDate: String? = null,
)