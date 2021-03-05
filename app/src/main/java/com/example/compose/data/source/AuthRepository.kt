package com.example.compose.data.source

import com.example.compose.data.source.local.AuthLocalDataSource
import com.example.compose.data.source.remote.AuthRemoteDataSource
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource) {

    suspend fun auth(username: String, password: String): Response<ResponseBody> {
        return authRemoteDataSource.auth(username = username, password = password)
    }

    fun token(): Flow<String?> {
        return authLocalDataSource.token()
    }

    suspend fun token(token: String) {
        return authLocalDataSource.token(token = token)
    }

    suspend fun clear() {
        return authLocalDataSource.clear()
    }
}