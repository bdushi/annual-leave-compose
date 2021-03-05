package com.example.compose.data.source

import com.example.compose.model.Leave
import com.example.compose.model.Page
import com.example.compose.model.dto.LeaveDto
import okhttp3.ResponseBody
import retrofit2.Response

interface LeaveDataSource {
    suspend fun leave(search: String, page: Int, size: Int) : Response<Page>
    suspend fun leave(leaveDto: LeaveDto) : Response<ResponseBody>
}