package com.hp.learnkotlin.ui.api.retrofit

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Invocation

class ResponseInterceptor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.e("Error", "1")
        val request = chain.request()
        Log.e("Error", "2")
        try {
            val response = chain.proceed(request)
            Log.e("Error", "3")
            if (!response.isSuccessful) {
                // Handle unsuccessful response globally
                // For example, you might want to throw a custom exception
                val errorMessage = "Request failed with code: ${response.code}"
                //val customResponse = CustomResult.error<String>(errorMessage)
                // You can also log the error here if needed
                // Log.e("CustomInterceptor", errorMessage)
                // Wrap the error message in your custom Result type and return it
                Response.Builder()
                    .request(response.request)
                    .protocol(response.protocol)
                    .code(response.code)
                    .message(response.message)
                    .headers(response.headers)
                    .body(errorMessage.toResponseBody())
                    .build()
            }
            val invocation = request.tag(Invocation::class.java)
            if (invocation != null) {
                // Check if the method has the Authenticate annotation
                val hasToken = invocation.method().annotations.any { it.annotationClass == Authenticate::class }
                if (hasToken) {
                    // Log that the method has the Authenticate annotation
                    val responseBody = response.body?.string()
                    val jsonObject = Gson().fromJson(responseBody, JsonObject::class.java)
                    val token = jsonObject?.get("token")?.asString

                    if (token != null) {
                        // Store the token into shared preferences
                        val apiSharedPreferenceManager = ApiSharedPreference(context)
                        apiSharedPreferenceManager.saveToken(token)
                    }
                    return response.newBuilder()
                        .body(responseBody?.toResponseBody(response.body?.contentType()))
                        //.code(200)
                        .build()
                }
            }


            return response
        } catch (e: Exception) {
            return Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_2)
                .code(503)

                .message("response.Error")
                .headers(request.headers)
                .body("errorMessage.toResponseBody()".toResponseBody())
                .build()
        }
    }
}