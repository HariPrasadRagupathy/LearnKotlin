package com.hp.learnkotlin.ui.depen.hilt

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

}