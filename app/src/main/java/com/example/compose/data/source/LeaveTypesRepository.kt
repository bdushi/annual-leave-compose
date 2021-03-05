package com.example.compose.data.source

import com.example.compose.data.source.remote.LeaveTypesRemoteDataSource
import com.example.compose.model.LeaveTypes
import retrofit2.Response
import javax.inject.Inject

class LeaveTypesRepository @Inject constructor(private val leaveTypesDataSource: LeaveTypesRemoteDataSource){
    suspend fun leaveTypes() : Response<List<LeaveTypes>> {
        return leaveTypesDataSource.leaveType()
    }
}