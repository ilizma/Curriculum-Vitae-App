package com.ilizma.api.data.di

import com.ilizma.api.data.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideApi(
        retrofit: Retrofit,
    ): Api = retrofit.create(Api::class.java)

}