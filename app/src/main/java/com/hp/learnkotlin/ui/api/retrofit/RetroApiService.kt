package com.hp.learnkotlin.ui.api.retrofit

import android.util.Log
import com.hp.learnkotlin.ui.api.retrofit.request.SignInRequest
import com.hp.learnkotlin.ui.api.retrofit.response.SignInResponse
import com.hp.learnkotlin.ui.api.retrofit.response.UserResponse
import com.hp.learnkotlin.ui.pagger.remote.UserDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetroApiService {

    @POST("signin")
    @Authenticated
    suspend fun signIn(@Body signInRequest : SignInRequest) : Response<SignInResponse>

    @GET("checkCrash/getError")
    suspend fun checkError() : Response<String>

    @GET("usersByPager")
    suspend fun getUsersByPager(
        @Query("page") page : Int,
        @Query("pagecount") pageCount : Int) : Response<UserResponse>

    companion object{
        fun createClient() : RetroApiService{
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.12:8081/")
                .client(
                    OkHttpClient.Builder()
                    .addInterceptor(AuthInterceptor())
                        .addInterceptor(ResponseInterceptor())
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

suspend fun <T : Any> Response<T>.toResult(): Result<T> {
    return try {
        if (isSuccessful) {
            val body = body()
            if (body != null) {
                Result.success(body)
            } else {
                Result.failure(Throwable("Response body is null"))
            }
        } else {
            Log.e("Error","error din")
            val errorBodyString = errorBody()?.string()
            val errorMessage = errorBodyString ?: message()
            Result.failure(Throwable(errorMessage))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}