package com.example.compose.interceptor

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import com.example.compose.model.Error

class ErrorHandler @Inject constructor(private val retrofit: Retrofit) {
    fun customError(response: Response<*>): Error {
        val converter: Converter<ResponseBody, Error> = retrofit.responseBodyConverter(Error::class.java, arrayOfNulls(0))
        return try {
            if (response.errorBody() != null) {
                converter.convert(response.errorBody()) ?: Error(400, "Error Body is Null")
            } else {
                Error(400, "Error Body is Null")
            }
        } catch (e: Exception) {
            Error(400, e.message)
        }
    }

    fun parseError(response: Response<*>): java.lang.Error {
        val converter: Converter<ResponseBody, java.lang.Error> = retrofit.responseBodyConverter(java.lang.Error::class.java, arrayOfNulls(0))
        return try {
            if (response.errorBody() != null) {
                converter.convert(response.errorBody()) ?: java.lang.Error("Error Body is Null")
            } else {
                java.lang.Error("Error Body is Null")
            }
        } catch (e: Exception) {
            java.lang.Error(e.message)
        }
    }
}