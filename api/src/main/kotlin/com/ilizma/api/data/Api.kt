package com.ilizma.api.data

import com.ilizma.api.model.CurriculumVitaeDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.adapter.rxjava3.Result
import retrofit2.http.GET

interface Api {

    @GET("url")
    fun getCurriculumVitae(): Single<Result<CurriculumVitaeDTO>>

}