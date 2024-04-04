package com.hp.learnkotlin.ui.pagger.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.hp.learnkotlin.ui.api.retrofit.RetroApiService
import com.hp.learnkotlin.ui.pagger.local.UserDatabase
import com.hp.learnkotlin.ui.pagger.local.UserEntity
import com.hp.learnkotlin.ui.pagger.toUserEntity

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator(
    private val userDb: UserDatabase,
    private val userApi: RetroApiService
) : RemoteMediator<Int, UserEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.userId.toInt() / state.config.pageSize) + 1
                    }
                }
            }

            val users = userApi.getUsersByPager(
                page = loadKey,
                pageCount = state.config.pageSize
            ).body()?.users ?: arrayListOf()

            userDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    userDb.dao.clearAll()
                }
                val userEntities = users.map { it.toUserEntity() }
                userDb.dao.upsertAll(userEntities)
            }

            MediatorResult.Success(endOfPaginationReached = users.isEmpty())

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}