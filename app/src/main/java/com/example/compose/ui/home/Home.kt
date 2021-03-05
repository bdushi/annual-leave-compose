package com.example.compose.ui.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.compose.ui.signin.AuthViewModel
import com.example.compose.R

@Composable
fun Home(
    addLeave: () -> Unit,
    leaveViewModel: LeaveViewModel,
    authViewModel: AuthViewModel
) {
    leaveViewModel.leave()
    val onClickRetry: () -> Unit
    Scaffold(
            topBar = {
                TopAppBar(
                        title = { Text(text = "Leave") },
                        actions = {
                            IconButton(onClick = {
                                authViewModel.logout()
                            }) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_logout), contentDescription = "LogOut"
                                )
                            }
                        }

                )
            },
            floatingActionButton = {
                FloatingActionButton(
                        content = { Icon(Icons.Rounded.Add, contentDescription = null) },
                        onClick = addLeave
                )
            },
            floatingActionButtonPosition = FabPosition.End,
            isFloatingActionButtonDocked = true,
            content = {
                LeavePagingListView(leaveViewModel.pagedLeaves)
            }
    )
}