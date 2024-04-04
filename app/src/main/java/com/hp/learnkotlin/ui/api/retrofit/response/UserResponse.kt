package com.hp.learnkotlin.ui.api.retrofit.response

import com.hp.learnkotlin.ui.pagger.remote.UserDto


data class UserResponse (
    val users : ArrayList<UserDto> = arrayListOf()
)