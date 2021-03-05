package com.example.compose.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.compose.data.source.LeaveRepository
import com.example.compose.data.source.LeaveTypesRepository
import com.example.compose.interceptor.ErrorHandler
import com.example.compose.model.Leave
import com.example.compose.model.LeaveTypes
import com.example.compose.model.dto.LeaveDto
import com.example.compose.page.LeavePageSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.Exception
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class LeaveViewModel @Inject constructor(
        private val leaveRepository: LeaveRepository,
        private val leaveTypesRepository: LeaveTypesRepository,
        private val errorHandler: ErrorHandler
): ViewModel() {

    var leaves: List<Leave> by mutableStateOf(listOf())
        private set

    var leaveTypes: List<LeaveTypes> by mutableStateOf(listOf())
        private set


    var startDate:String by mutableStateOf(LocalDate.now().toString())

    val pagedLeaves: Flow<PagingData<Leave>> = Pager(
        PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        )
    ) {
        LeavePageSource(leaveRepository = leaveRepository)
    }.flow

    fun leave() {
        viewModelScope.launch {
            try {
                val response = leaveRepository.leave("", 0, 10)
                if(response.isSuccessful) {
                    response.body()?.let {
                        leaves = it.leave
                    }
                } else {
                    Log.d(LeaveViewModel::class.java.name, errorHandler.parseError(response).message.toString())
                }
            } catch (ex: Exception) {
                Log.d(LeaveViewModel::class.java.name, ex.message.toString())
            }
        }
    }

    fun leaveTypes() {
        viewModelScope.launch {
            try {
                val response = leaveTypesRepository.leaveTypes()
                if(response.isSuccessful) {
                    response.body()?.let {
                        leaveTypes = it
                    }
                } else {
                    Log.d(LeaveViewModel::class.java.name, errorHandler.parseError(response).message.toString())
                }
            } catch (ex: Exception) {
                Log.d(LeaveViewModel::class.java.name, ex.message.toString())
            }
        }
    }

    fun leave(leaveDto: LeaveDto) {
        viewModelScope.launch {
            try {
                val response = leaveRepository.leave(leaveDto)
                if(response.isSuccessful) {
                    Log.d(LeaveViewModel::class.java.name, errorHandler.parseError(response).message.toString())
                } else {
                    Log.d(LeaveViewModel::class.java.name, errorHandler.parseError(response).message.toString())
                }
            } catch (ex: Exception) {
                Log.d(LeaveViewModel::class.java.name, ex.message.toString())
            }
        }
    }
}