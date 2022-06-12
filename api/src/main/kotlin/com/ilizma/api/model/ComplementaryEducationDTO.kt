package com.ilizma.api.model

import com.squareup.moshi.Json

data class ComplementaryEducationDTO(
    @Json(name = "type") val type: String? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "hours") val hours: String? = null,
    @Json(name = "date") val date: String? = null,
    @Json(name = "place") val place: String? = null,
)