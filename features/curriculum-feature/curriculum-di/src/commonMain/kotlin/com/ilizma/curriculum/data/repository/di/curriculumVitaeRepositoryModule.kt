package com.ilizma.curriculum.data.repository.di

import com.ilizma.curriculum.data.mapper.*
import com.ilizma.curriculum.data.repository.CurriculumVitaeRepositoryImp
import com.ilizma.curriculum.domain.repository.CurriculumVitaeRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val curriculumVitaeRepositoryModule: Module = module {

    factory<CurriculumVitaeRepository> {
        CurriculumVitaeRepositoryImp(
            dataSource = get(),
            cache = get(),
            mapper = CurriculumVitaeMapper(
                personalDataMapper = PersonalDataMapper(),
                educationMapper = EducationMapper(),
                complementaryEducationMapper = ComplementaryEducationMapper(),
                workMapper = WorkMapper(),
                otherMapper = OtherMapper()
            ),
        )
    }

}