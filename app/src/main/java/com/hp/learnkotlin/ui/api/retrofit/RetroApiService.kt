package com.hp.learnkotlin.ui.api.retrofit

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.hp.learnkotlin.ui.api.retrofit.request.SignInRequest
import com.hp.learnkotlin.ui.api.retrofit.request.SignUpRequest
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


    @POST("signup")
    suspend fun signUp(@Body signUpRequest : SignUpRequest) : Response<SignInResponse>


    @POST("signin")
    @Authenticate
    suspend fun signIn(@Body signInRequest : SignInRequest) : Response<SignInResponse>

    @Authenticate
    @GET("checkCrash/getResponse")
    suspend fun checkError() : Response<SignInResponse>

    @GET("usersByPager")
    suspend fun getUsersByPager(
        @Query("page") page : Int,
        @Query("pagecount") pageCount : Int) : Response<UserResponse>

    companion object{

        private val gson: Gson = GsonBuilder()
            .create()
        fun createClient(context: Context) : RetroApiService{
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.10:8081/")
                .client(
                    OkHttpClient.Builder()
                    .addInterceptor(AuthInterceptor(context))
                    .addInterceptor(ResponseInterceptor(context))
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
annotation class Authenticate

@Target(AnnotationTarget.FUNCTION)
annotation class Authenticated

fun <T : Any> Response<T>.toResult(): Result<T> {
    return try {
        if (isSuccessful) {
            val body = body()
            val code = code()

            if (body != null && body !is DefaultErrorResponse) {
                Log.e("response Data","->${body.toString()}->code=$code")

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