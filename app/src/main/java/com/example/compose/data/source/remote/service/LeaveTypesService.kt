package com.example.compose.data.source.remote.service

import com.example.compose.model.LeaveTypes
import retrofit2.Response
import retrofit2.http.GET

interface LeaveTypesService {
    @GET("/leaveTypes")
    suspend fun leaveTypes() : Response<List<LeaveTypes>>
}