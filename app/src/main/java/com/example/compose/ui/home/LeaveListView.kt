package com.example.compose.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.compose.model.Leave

@Composable
fun LeaveListView(leaves: List<Leave>) {
    LazyColumn {
        items(
            items = leaves,
            key = { leave -> leave.id })  {
                leave -> LeaveItem(leave = leave)
        }
    }
}