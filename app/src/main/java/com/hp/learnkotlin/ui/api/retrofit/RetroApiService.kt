package com.hp.learnkotlin.ui.api.retrofit

import com.hp.learnkotlin.ui.api.retrofit.request.SignInRequest
import com.hp.learnkotlin.ui.api.retrofit.response.SignInResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetroApiService {

    @POST("signin")
    @Authenticated
    suspend fun signIn(@Body signInRequest : SignInRequest) : Response<SignInResponse>

    companion object{
        fun createClient() : RetroApiService{
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.10:8081/")
                .client(
                    OkHttpClient.Builder()
                    .addInterceptor(AuthInterceptor())
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(RetroApiService::class.java)
        }
    }


}

@Target(AnnotationTarget.FUNCTION)
annotation class Authenticated