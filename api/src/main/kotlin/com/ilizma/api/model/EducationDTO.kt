package com.ilizma.api.model

import com.squareup.moshi.Json

data class EducationDTO(
    @Json(name = "title") val title: String? = null,
    @Json(name = "place") val place: String? = null,
    @Json(name = "startDate") val startDate: String? = null,
    @Json(name = "endDate") val endDate: String? = null,
    @Json(name = "currently") val currently: Boolean? = null,
)