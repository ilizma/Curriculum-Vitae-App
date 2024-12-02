package com.ilizma.curriculum.data.mapper

import com.ilizma.curriculum.data.model.CurriculumVitaeDTO
import com.ilizma.curriculum.domain.model.CurriculumVitae
import com.ilizma.curriculum.data.model.CurriculumVitae as DataCurriculumVitae

class CurriculumVitaeMapper(
    private val personalDataMapper: PersonalDataMapper,
    private val educationMapper: EducationMapper,
    private val complementaryEducationMapper: ComplementaryEducationMapper,
    private val workMapper: WorkMapper,
    private val otherMapper: OtherMapper,
) {

    fun from(
        data: CurriculumVitaeDTO,
    ): DataCurriculumVitae = DataCurriculumVitae(
        personalData = data.personal_data?.let { personalDataMapper.from(it) }
            ?: throw nullParameterException("curriculumVitae.personalData"),
        education = data.education?.map { educationMapper.from(it) }
            ?: throw nullParameterException("curriculumVitae.education"),
        complementaryEducation = data.complementary_education?.map {
            complementaryEducationMapper.from(it)
        } ?: listOf(),
        work = data.work?.map { workMapper.from(it) } ?: throw nullParameterException("education"),
        description = data.description ?: "",
        skills = data.skills ?: listOf(),
        other = data.other?.map { otherMapper.from(it) } ?: listOf()
    )

    fun from(
        data: DataCurriculumVitae,
    ): CurriculumVitae = CurriculumVitae(
        personalData = personalDataMapper.from(data.personalData),
        education = data.education.map { educationMapper.from(it) },
        complementaryEducation = data.complementaryEducation.map {
            complementaryEducationMapper.from(it)
        },
        work = data.work.map { workMapper.from(it) },
        description = data.description,
        skills = data.skills,
        other = data.other.map { otherMapper.from(it) }
    )

    private fun nullParameterException(
        parameter: String
    ): IllegalArgumentException = IllegalArgumentException("$parameter can not be null")

}