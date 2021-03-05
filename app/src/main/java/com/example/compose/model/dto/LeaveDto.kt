package com.example.compose.model.dto

import com.example.compose.model.LeaveTypes
import java.time.LocalDateTime

data class LeaveDto(
        val description: String,
        val comment: String,
        val leaveTypes: LeaveTypes,
        val startDate: LocalDateTime,
        val endDate: LocalDateTime
)