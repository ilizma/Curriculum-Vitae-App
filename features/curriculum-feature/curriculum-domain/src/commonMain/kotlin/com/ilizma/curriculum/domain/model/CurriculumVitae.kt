package com.ilizma.curriculum.domain.model

data class CurriculumVitae(
    val personalData: PersonalData,
    val education: List<Education>,
    val complementaryEducation: List<ComplementaryEducation>,
    val work: List<Work>,
    val description: String,
    val skills: List<String>,
    val other: List<Other>,
)