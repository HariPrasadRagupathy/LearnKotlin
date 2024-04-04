package com.hp.learnkotlin.ui.pagger.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class UserEntity(
    @PrimaryKey
    val userId: String,
    val userName: String,
    val userPhone: String,
    val userCountryCode: String,
    val userEmail: String,
    val userStatus: String?,
    val userRole: String?
)
