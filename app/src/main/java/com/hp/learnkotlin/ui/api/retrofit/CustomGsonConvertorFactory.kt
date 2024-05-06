package com.hp.learnkotlin.ui.api.retrofit

import android.util.Log
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class CustomGsonConverterFactory private constructor(private val gson: Gson) : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val adapter: TypeAdapter<*> = gson.getAdapter(TypeToken.get(type))
        return Converter { value: ResponseBody ->
            value.use { responseBody ->
                val jsonReader = gson.newJsonReader(responseBody.charStream())
                try {
                    adapter.read(jsonReader)
                } catch (e: IllegalStateException) {
                    Log.e("response","->${responseBody.string()}")
                    // Handle the IllegalStateException here
                    // For example, log the error
                    e.printStackTrace()
                    // Return a default value or an appropriate error response
                    // You may need to define a default response model class
                    DefaultErrorResponse()
                } finally {
                    responseBody.close()
                }
            }
        }
    }

    companion object {
        fun create(gson: Gson): CustomGsonConverterFactory {
            return CustomGsonConverterFactory(gson)
        }
    }
}

data class DefaultErrorResponse(
    val errorCode: Int = -1,
    val errorMessage: String = "An unexpected error occurred"
)