package com.hp.learnkotlin.ui.pagger.remote

import com.google.gson.annotations.SerializedName

data class UserDto(
    val userId: String,
    val userName: String,
    @SerializedName("phoneNumber")
    val userPhone: String,
    @SerializedName("countryCode")
    val userCountryCode: String,
    @SerializedName("emailId")
    val userEmail: String,
    @SerializedName("status")
    val userStatus: String?,
    @SerializedName("userRole")
    val userRole: String?
)