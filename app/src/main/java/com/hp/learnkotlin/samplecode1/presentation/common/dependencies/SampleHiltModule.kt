package com.hp.learnkotlin.samplecode1.presentation.common.dependencies

import com.hp.learnkotlin.samplecode1.repositories.SampleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SampleHiltModule {

    @Singleton
    @Provides
    fun getSampleRepository() : SampleRepository{
        return SampleRepository()
    }
}