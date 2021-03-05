package com.example.compose.data.source.remote

import com.example.compose.data.source.LeaveDataSource
import com.example.compose.data.source.remote.service.LeaveService
import com.example.compose.model.Leave
import com.example.compose.model.Page
import com.example.compose.model.dto.LeaveDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Query
import javax.inject.Inject

class LeaveRemoteDataSource @Inject constructor(private val leaveService: LeaveService) : LeaveDataSource {
    override suspend fun leave(search: String, page: Int, size: Int): Response<Page> {
        return leaveService.leave(search = search, page = page, size = size)
    }

    override suspend fun leave(leaveDto: LeaveDto): Response<ResponseBody> {
        return leaveService.leave(leaveDto = leaveDto)
    }
}