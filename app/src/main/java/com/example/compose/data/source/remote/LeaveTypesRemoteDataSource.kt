package com.example.compose.data.source.remote

import com.example.compose.data.source.LeaveTypesDataSource
import com.example.compose.data.source.remote.service.LeaveTypesService
import com.example.compose.model.LeaveTypes
import retrofit2.Response
import javax.inject.Inject

class LeaveTypesRemoteDataSource @Inject constructor(private val leaveTypesService: LeaveTypesService) : LeaveTypesDataSource {
    override suspend fun leaveType(): Response<List<LeaveTypes>> {
        return leaveTypesService.leaveTypes()
    }
}