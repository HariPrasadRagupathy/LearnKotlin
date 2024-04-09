package com.hp.learnkotlin.ui.depen.hilt

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.hp.learnkotlin.ui.api.retrofit.RetroApiService
import com.hp.learnkotlin.ui.pagger.local.UserDatabase
import com.hp.learnkotlin.ui.pagger.local.UserEntity
import com.hp.learnkotlin.ui.pagger.remote.UserRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Singleton
    @Provides
    fun getHiltDem(): HiltDemo {
        return HiltDemoImpl()
    }

    @Singleton
    @Provides
    fun getRetroApiService(): RetroApiService {
        return RetroApiService.createClient()
    }

    @Singleton
    @Provides
    fun getUserDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(context, UserDatabase::class.java, "users.db").build()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Singleton
    @Provides
    fun provideUserPager(userDb : UserDatabase, userApi : RetroApiService) : Pager<Int, UserEntity> {
       return Pager(
           config = PagingConfig(pageSize = 10),
           remoteMediator = UserRemoteMediator(userDb,userApi),
           pagingSourceFactory = {userDb.dao.pagingSource()}

       )
    }



}