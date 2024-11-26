package com.ilizma.curriculum.data.repository

import com.ilizma.curriculum.data.cache.CurriculumVitaeCache
import com.ilizma.curriculum.data.datasource.CurriculumVitaeDataSource
import com.ilizma.curriculum.data.mapper.CurriculumVitaeMapper
import com.ilizma.curriculum.domain.model.CurriculumVitae
import com.ilizma.curriculum.domain.repository.CurriculumVitaeRepository
import com.ilizma.curriculum.data.model.CurriculumVitae as DataCurriculumVitae

class CurriculumVitaeRepositoryImp(
    private val dataSource: CurriculumVitaeDataSource,
    private val cache: CurriculumVitaeCache,
    private val mapper: CurriculumVitaeMapper,
) : CurriculumVitaeRepository {

    override suspend fun get(
    ): CurriculumVitae = (getFromCache() ?: getFromApiAndSaveCache())
        .let { mapper.from(it) }

    private fun getFromCache(
    ): DataCurriculumVitae? = cache.cache

    private suspend fun getFromApiAndSaveCache(
    ): DataCurriculumVitae = dataSource.get()
        .also { cache.cache = it }

}