package com.ilizma.curriculum.data.datasource.di

import com.ilizma.curriculum.data.datasource.CurriculumVitaeDataSource
import com.ilizma.curriculum.data.datasource.CurriculumVitaeDataSourceImp
import com.ilizma.curriculum.data.mapper.ComplementaryEducationMapper
import com.ilizma.curriculum.data.mapper.CurriculumVitaeMapper
import com.ilizma.curriculum.data.mapper.EducationMapper
import com.ilizma.curriculum.data.mapper.OtherMapper
import com.ilizma.curriculum.data.mapper.PersonalDataMapper
import com.ilizma.curriculum.data.mapper.WorkMapper
import com.ilizma.curriculum.di.BuildKonfig
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import org.koin.core.module.Module
import org.koin.dsl.module

val curriculumVitaeDataSourceModule: Module = module {

    factory<CurriculumVitaeDataSource> {
        CurriculumVitaeDataSourceImp(
            cvId = BuildKonfig.CV_ID,
            firestore = Firebase.firestore,
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