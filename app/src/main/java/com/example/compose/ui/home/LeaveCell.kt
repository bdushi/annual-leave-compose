package com.example.compose.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LeaveCell(title: String, value: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(1.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value
        )
    }
}