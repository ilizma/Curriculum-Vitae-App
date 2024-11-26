package com.ilizma.curriculum.data.datasource

import com.ilizma.api.model.CurriculumVitaeDTO
import com.ilizma.curriculum.data.mapper.CurriculumVitaeMapper
import com.ilizma.curriculum.data.model.CurriculumVitae
import dev.gitlive.firebase.firestore.FirebaseFirestore

private const val CVS_COLLECTION = "CVs"

class CurriculumVitaeDataSourceImp(
    private val cvId: String,
    private val firestore: FirebaseFirestore,
    private val mapper: CurriculumVitaeMapper,
) : CurriculumVitaeDataSource {

    override suspend fun get(
    ): CurriculumVitae = firestore.collection(CVS_COLLECTION)
        .get()
        .documents.firstOrNull { it.id == cvId }
        ?.data<CurriculumVitaeDTO>()
        ?.let { mapper.from(it) }
        ?: throw NoSuchElementException("No CV found")
}