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
) {
    fun doesMatchSearchQuery(query : String) : Boolean{
        val matchingCombinations = listOf(
            "$userName",
            "$userEmail"
        )

        return matchingCombinations.any{
            it.contains(query,true)
        }
    }
}
