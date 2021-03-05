package com.example.compose.data.source

import com.example.compose.data.source.remote.LeaveRemoteDataSource
import com.example.compose.model.Leave
import com.example.compose.model.Page
import com.example.compose.model.dto.LeaveDto
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class LeaveRepository @Inject constructor(private val leaveDataSource: LeaveRemoteDataSource) {
    suspend fun leave(search: String, page: Int, size: Int) : Response<Page> {
        return leaveDataSource.leave(search = search, page = page, size = size)
    }

    suspend fun leave(leaveDto: LeaveDto) : Response<ResponseBody> {
        return leaveDataSource.leave(leaveDto = leaveDto)
    }
}