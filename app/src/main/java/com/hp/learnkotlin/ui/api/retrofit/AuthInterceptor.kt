package com.hp.learnkotlin.ui.api.retrofit

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation

class AuthInterceptor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val invocation = chain.request().tag(Invocation::class.java)
            ?: return chain.proceed(chain.request())

        val shouldAttachAuthHeader = invocation.method().annotations.any { it.annotationClass == Authenticate::class }

        return if(shouldAttachAuthHeader){
            val apiSharedPreferenceManager = ApiSharedPreference(context)
            apiSharedPreferenceManager.getToken()
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .addHeader("Authorization","Bearer ${apiSharedPreferenceManager.getToken()}")
                    .build()
            )
        } else {
            chain.proceed(chain.request())
        }
    }
}