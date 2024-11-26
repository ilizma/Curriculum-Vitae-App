package com.ilizma.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ComplementaryEducationDTO(
    @SerialName(value = "type") val type: String? = null,
    @SerialName(value = "title") val title: String? = null,
    @SerialName(value = "hours") val hours: String? = null,
    @SerialName(value = "date") val date: String? = null,
    @SerialName(value = "place") val place: String? = null,
)