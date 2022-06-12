package com.ilizma.api.model

import com.squareup.moshi.Json

data class OtherDTO(
    @Json(name = "title") val title: String? = null,
    @Json(name = "description") val description: String? = null,
)