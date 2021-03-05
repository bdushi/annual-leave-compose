package com.example.compose.data.source

import com.example.compose.model.LeaveTypes
import retrofit2.Response

interface LeaveTypesDataSource {
    suspend fun leaveType() : Response<List<LeaveTypes>>
}