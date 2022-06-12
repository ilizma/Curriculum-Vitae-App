package com.ilizma.api.model

import com.squareup.moshi.Json

data class WorkDTO(
    @Json(name = "title") val title: String? = null,
    @Json(name = "task") val task: String? = null,
    @Json(name = "company") val company: String? = null,
    @Json(name = "place") val place: String? = null,
    @Json(name = "startDate") val startDate: String? = null,
    @Json(name = "endDate") val endDate: String? = null,
    @Json(name = "currently") val currently: Boolean? = null,
)