package com.hp.learnkotlin.ui.pagger.domain

import com.google.gson.annotations.SerializedName

data class User(
    val userId: String,
    val userName: String,
    val userPhone: String,
    val userCountryCode: String,
    val userEmail: String,
    val userStatus: String?,
    val userRole: String?
)
