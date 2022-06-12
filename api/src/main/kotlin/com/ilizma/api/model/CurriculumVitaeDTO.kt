package com.ilizma.api.model

import com.squareup.moshi.Json

data class CurriculumVitaeDTO(
    @Json(name = "personalData") val personalData: PersonalDataDTO? = null,
    @Json(name = "education") val education: List<EducationDTO>? = null,
    @Json(name = "complementaryEducation") val complementaryEducation: List<ComplementaryEducationDTO>? = null,
    @Json(name = "work") val work: List<WorkDTO>? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "skills") val skills: List<String>? = null,
    @Json(name = "other") val other: List<OtherDTO>? = null,
)