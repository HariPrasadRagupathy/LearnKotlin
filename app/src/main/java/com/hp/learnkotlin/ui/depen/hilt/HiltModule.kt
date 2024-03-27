package com.hp.learnkotlin.ui.depen.hilt

import com.hp.learnkotlin.ui.api.retrofit.RetroApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule{

    @Singleton
    @Provides
    fun getHiltDem() : HiltDemo{
        return HiltDemoImpl()
    }

    @Singleton
    @Provides
    fun getRetroApiService() : RetroApiService{
        return RetroApiService.createClient()
    }

}