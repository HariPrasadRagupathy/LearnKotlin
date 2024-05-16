package com.hp.learnkotlin.ui.api.retrofit

import android.content.Context


class ApiSharedPreference(private val context: Context) {
    companion object {
        private const val PREF_NAME = "data_pref"
        private const val KEY_TOKEN = "token"
    }

    fun saveToken(token: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_TOKEN, token)
        editor.apply()
    }

    fun getToken(): String? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_TOKEN, "0")
    }
}