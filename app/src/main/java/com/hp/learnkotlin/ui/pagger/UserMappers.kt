package com.hp.learnkotlin.ui.pagger

import com.hp.learnkotlin.ui.pagger.domain.User
import com.hp.learnkotlin.ui.pagger.local.UserEntity
import com.hp.learnkotlin.ui.pagger.remote.UserDto

fun UserDto.toUserEntity() : UserEntity{
    return UserEntity(
        userId = userId,
        userName = userName,
        userPhone = userPhone,
        userCountryCode = userCountryCode,
        userEmail = userEmail,
        userStatus = userStatus,
        userRole = userRole
    )
}

fun UserEntity.toUser() : User {
    return User(
        userId = userId,
        userName = userName,
        userPhone = userPhone,
        userCountryCode = userCountryCode,
        userEmail = userEmail,
        userStatus = userStatus,
        userRole = userRole
    )
}