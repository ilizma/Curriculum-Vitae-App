package com.ilizma.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurriculumVitaeDTO(
    @SerialName(value = "personal_data") val personal_data: PersonalDataDTO? = null,
    @SerialName(value = "education") val education: List<EducationDTO>? = null,
    @SerialName(value = "complementary_education") val complementary_education: List<ComplementaryEducationDTO>? = null,
    @SerialName(value = "work") val work: List<WorkDTO>? = null,
    @SerialName(value = "description") val description: String? = null,
    @SerialName(value = "skills") val skills: List<String>? = null,
    @SerialName(value = "other") val other: List<OtherDTO>? = null,
)