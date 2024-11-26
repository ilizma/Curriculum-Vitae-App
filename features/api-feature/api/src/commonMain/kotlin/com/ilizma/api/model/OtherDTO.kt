package com.ilizma.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OtherDTO(
    @SerialName(value = "title") val title: String? = null,
    @SerialName(value = "description") val description: String? = null,
    @SerialName(value = "link") val link: String? = null,
)