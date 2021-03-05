package com.example.compose.model

import java.time.LocalDateTime

data class Leave(
    val id: Long,
    val createDate: LocalDateTime,
    val startDate:LocalDateTime,
    val endDate: LocalDateTime,
    val description: String,
    val comment: String,
    val requestedBy: User?,
    val leaveTypes: LeaveTypes,
    val approved: List<Approved>)