package com.example.compose.common

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Util {
    fun format(localDateTime: LocalDateTime): String {
        return localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm:ss"))
    }
}