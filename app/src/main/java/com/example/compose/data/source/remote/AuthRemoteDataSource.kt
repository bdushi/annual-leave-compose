package com.example.compose.data.source.remote

import com.example.compose.data.source.AuthDataSource
import com.example.compose.data.source.remote.service.AuthService
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val authService: AuthService) : AuthDataSource {
    override suspend fun auth(username: String, password: String): Response<ResponseBody> {
        return authService.auth(username = username, password = password)
    }

    override fun token(): Flow<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun token(token: String) {
        TODO("Not yet implemented")
    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }
}