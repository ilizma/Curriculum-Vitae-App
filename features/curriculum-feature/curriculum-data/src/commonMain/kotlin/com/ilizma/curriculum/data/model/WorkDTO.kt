package com.ilizma.curriculum.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorkDTO(
    @SerialName(value = "title") val title: String? = null,
    @SerialName(value = "task") val task: String? = null,
    @SerialName(value = "company") val company: String? = null,
    @SerialName(value = "place") val place: String? = null,
    @SerialName(value = "start_date") val start_date: String? = null,
    @SerialName(value = "end_date") val end_date: String? = null,
    @SerialName(value = "currently") val currently: Boolean? = null,
)