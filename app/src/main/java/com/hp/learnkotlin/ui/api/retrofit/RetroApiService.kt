package com.hp.learnkotlin.ui.api.retrofit

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
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
import java.lang.reflect.Type

interface RetroApiService {

    @POST("signin")
    @Authenticated
    suspend fun signIn(@Body signInRequest : SignInRequest) : Response<SignInResponse>

    @GET("checkCrash/getRespons")
    suspend fun checkError() : Response<String>

    @GET("usersByPager")
    suspend fun getUsersByPager(
        @Query("page") page : Int,
        @Query("pagecount") pageCount : Int) : Response<UserResponse>

    companion object{

        private val gson: Gson = GsonBuilder()
            .create()
        fun createClient() : RetroApiService{
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.8:8081/")
                .client(
                    OkHttpClient.Builder()
                    .addInterceptor(AuthInterceptor())
                    .addInterceptor(ResponseInterceptor())
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
                )
                .addConverterFactory(CustomGsonConverterFactory.create(gson))
                .build()

            return retrofit.create(RetroApiService::class.java)
        }
    }


}

class CustomDeserializer : JsonDeserializer<Any> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): Any {
        return try {
            Gson().fromJson(json, typeOfT)
        } catch (e: JsonParseException) {
            Log.e("JSON Deserialization", "Error: ${e.message}")
            "error"
           // DefaultErrorResponse() // You need to define your own DefaultErrorResponse class
        }
    }
}

@Target(AnnotationTarget.FUNCTION)
annotation class Authenticated

fun <T : Any> Response<T>.toResult(): Result<T> {
    return try {
        if (isSuccessful) {
            val body = body()

            if (body != null && body !is DefaultErrorResponse) {
                Log.e("response Data","->${body.toString()}")

                Result.success(body)
            } else {
                Result.failure(Throwable("Response body is null"))
            }
        } else {
            val errorBodyString = errorBody()?.string()
            val errorMessage = errorBodyString ?: message()
            Result.failure(Throwable(errorMessage))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}