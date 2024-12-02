package com.ilizma.curriculum.data.maper

import com.ilizma.curriculum.data.mapper.ComplementaryEducationMapper
import com.ilizma.curriculum.data.mapper.CurriculumVitaeMapper
import com.ilizma.curriculum.data.mapper.EducationMapper
import com.ilizma.curriculum.data.mapper.OtherMapper
import com.ilizma.curriculum.data.mapper.PersonalDataMapper
import com.ilizma.curriculum.data.mapper.WorkMapper
import com.ilizma.curriculum.data.model.ComplementaryEducationDTO
import com.ilizma.curriculum.data.model.CurriculumVitaeDTO
import com.ilizma.curriculum.data.model.EducationDTO
import com.ilizma.curriculum.data.model.OtherDTO
import com.ilizma.curriculum.data.model.PersonalDataDTO
import com.ilizma.curriculum.data.model.WorkDTO
import com.ilizma.curriculum.domain.model.ComplementaryEducation
import com.ilizma.curriculum.domain.model.CurriculumVitae
import com.ilizma.curriculum.domain.model.Education
import com.ilizma.curriculum.domain.model.Other
import com.ilizma.curriculum.domain.model.PersonalData
import com.ilizma.curriculum.domain.model.Work
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import com.ilizma.curriculum.data.model.ComplementaryEducation as DataComplementaryEducation
import com.ilizma.curriculum.data.model.CurriculumVitae as DataCurriculumVitae
import com.ilizma.curriculum.data.model.Education as DataEducation
import com.ilizma.curriculum.data.model.Other as DataOther
import com.ilizma.curriculum.data.model.PersonalData as DataPersonalData
import com.ilizma.curriculum.data.model.Work as DataWork

class CurriculumVitaeMapperTest {

    @RelaxedMockK
    private lateinit var personalDataMapper: PersonalDataMapper

    @RelaxedMockK
    private lateinit var educationMapper: EducationMapper

    @RelaxedMockK
    private lateinit var complementaryEducationMapper: ComplementaryEducationMapper

    @RelaxedMockK
    private lateinit var workMapper: WorkMapper

    @RelaxedMockK
    private lateinit var otherMapper: OtherMapper

    @RelaxedMockK
    private lateinit var personalDataDTO: PersonalDataDTO

    @RelaxedMockK
    private lateinit var dataPersonalData: DataPersonalData

    @RelaxedMockK
    private lateinit var personalData: PersonalData

    @RelaxedMockK
    private lateinit var educationDTO: EducationDTO

    @RelaxedMockK
    private lateinit var dataEducation: DataEducation

    @RelaxedMockK
    private lateinit var education: Education

    @RelaxedMockK
    private lateinit var complementaryEducationDTO: ComplementaryEducationDTO

    @RelaxedMockK
    private lateinit var dataComplementaryEducation: DataComplementaryEducation

    @RelaxedMockK
    private lateinit var complementaryEducation: ComplementaryEducation

    @RelaxedMockK
    private lateinit var workDTO: WorkDTO

    @RelaxedMockK
    private lateinit var dataWork: DataWork

    @RelaxedMockK
    private lateinit var work: Work

    @RelaxedMockK
    private lateinit var otherDTO: OtherDTO

    @RelaxedMockK
    private lateinit var dataOther: DataOther

    @RelaxedMockK
    private lateinit var other: Other

    private val description = "description"
    private val skill = "skill"

    private lateinit var mapper: CurriculumVitaeMapper

    init {
        MockKAnnotations.init(this)

        every { personalDataMapper.from(personalDataDTO) } returns dataPersonalData
        every { personalDataMapper.from(dataPersonalData) } returns personalData
        every { educationMapper.from(educationDTO) } returns dataEducation
        every { educationMapper.from(dataEducation) } returns education
        every { complementaryEducationMapper.from(complementaryEducationDTO) } returns dataComplementaryEducation
        every { complementaryEducationMapper.from(dataComplementaryEducation) } returns complementaryEducation
        every { workMapper.from(workDTO) } returns dataWork
        every { workMapper.from(dataWork) } returns work
        every { otherMapper.from(otherDTO) } returns dataOther
        every { otherMapper.from(dataOther) } returns other
    }

    @BeforeTest
    fun setup() {
        mapper = CurriculumVitaeMapper(
            personalDataMapper = personalDataMapper,
            educationMapper = educationMapper,
            complementaryEducationMapper = complementaryEducationMapper,
            workMapper = workMapper,
            otherMapper = otherMapper,
        )
    }

    @Test
    fun `given CurriculumVitaeDTO, when from, then result should be the expected DataCurriculumVitae`() {
        // given
        val data = CurriculumVitaeDTO(
            personal_data = personalDataDTO,
            education = listOf(educationDTO),
            complementary_education = listOf(complementaryEducationDTO),
            work = listOf(workDTO),
            description = description,
            skills = listOf(skill),
            other = listOf(otherDTO),
        )
        val expected = DataCurriculumVitae(
            personalData = dataPersonalData,
            education = listOf(dataEducation),
            complementaryEducation = listOf(dataComplementaryEducation),
            work = listOf(dataWork),
            description = description,
            skills = listOf(skill),
            other = listOf(dataOther),
        )

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given DataCurriculumVitae, when from, then result should be the expected CurriculumVitae`() {
        // given
        val data = DataCurriculumVitae(
            personalData = dataPersonalData,
            education = listOf(dataEducation),
            complementaryEducation = listOf(dataComplementaryEducation),
            work = listOf(dataWork),
            description = description,
            skills = listOf(skill),
            other = listOf(dataOther),
        )
        val expected = CurriculumVitae(
            personalData = personalData,
            education = listOf(education),
            complementaryEducation = listOf(complementaryEducation),
            work = listOf(work),
            description = description,
            skills = listOf(skill),
            other = listOf(other),
        )

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

}