package com.example.compose.ui.addleave

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.example.compose.R
import com.example.compose.model.LeaveTypes
import com.example.compose.model.dto.LeaveDto
import com.example.compose.ui.home.LeaveViewModel
import java.time.LocalDateTime

@Composable
fun AddLeave(
        datePicker: () -> Unit,
        success: () -> Unit,
        navigateUp: () -> Unit,
        leaveViewModel: LeaveViewModel
) {
    leaveViewModel.leaveTypes()
    var description by remember { mutableStateOf("")}
    var comment by remember { mutableStateOf("")}
    var startDate by remember { mutableStateOf("")}
    var leaveType by remember { mutableStateOf(LeaveTypes(1, "", "")) }

    var showMenu by remember { mutableStateOf( false ) }
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                title = { Text(text = stringResource(id = R.string.add_leave)) },
                navigationIcon = {
                    IconButton(onClick = navigateUp, content = { Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "Back to Home")
                    })
                }
            )
        },

        content = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    value = description,
                    onValueChange = {
                        description = it
                    },
                    label = { Text(text = stringResource(id = R.string.description)) }
                )
                TextField(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    value = comment,
                    onValueChange = {
                        comment = it
                    },
                    label = { Text(text = stringResource(id = R.string.comment)) }
                )
                TextButton(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    onClick = datePicker
                ) {
                    Text(text = leaveViewModel.startDate)
                }
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    TextButton(
                        onClick = { showMenu = true },
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        Text(text =
                                if (leaveViewModel.leaveTypes.isNotEmpty())
                                    leaveViewModel.leaveTypes[selectedIndex].description
                                else
                                    "Leave Types",
                            color = colorResource(id = android.R.color.black)
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false },
                        properties = PopupProperties(
                            dismissOnBackPress = true,
                            dismissOnClickOutside = true,
                            securePolicy = SecureFlagPolicy.SecureOn
                        ),
                        offset = DpOffset(10.dp, 0.dp),
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                    ) {
                        leaveViewModel.leaveTypes.forEachIndexed { index, type ->
                            DropdownMenuItem(
                                onClick = {
                                    showMenu = false
                                    selectedIndex = index
                                    leaveType = leaveViewModel.leaveTypes[index]
                                }
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    text = type.description
                                )
                            }
                        }
                    }
                }
//                DropdownMenu(
//                    toggle = {
//                        Text(
//                            text = if (leaveViewModel.leaveTypes.isNotEmpty())
//                                leaveViewModel.leaveTypes[selectedIndex].description
//                            else
//                                "Leave Types",
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .clickable(onClick = { showMenu = true })
//                        )
//                    },
//                    expanded = showMenu,
//                    onDismissRequest = { showMenu = false },
//                    toggleModifier = Modifier
//                        .padding(8.dp)
//                        .fillMaxWidth(),
//                    dropdownOffset = Position(0.dp, 0.dp),
//                    dropdownModifier = Modifier
//                        .padding(8.dp)
//                        .fillMaxWidth()
//
//                ) {
//                    leaveViewModel.leaveTypes.forEachIndexed { index, type ->
//                        DropdownMenuItem(
//                            onClick = {
//                                showMenu = false
//                                selectedIndex = index
//                                leaveType = leaveViewModel.leaveTypes[index]
//                            }
//                        ) {
//                            Text(
//                                modifier = Modifier
//                                    .padding(8.dp)
//                                    .fillMaxWidth(),
//                                text = type.description
//                            )
//                        }
//                    }
//                }

                Button(
                    enabled = description.isNotEmpty() && comment.isNotEmpty(),
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.End),
                    onClick = {
                        leaveViewModel.leave(
                            LeaveDto(
                                description = description,
                                comment = comment,
                                leaveTypes = leaveType,
                                LocalDateTime.now(),
                                LocalDateTime.now()
                            )
                        )
                        success.invoke()
                    }) {
                    Text(text = stringResource(id = R.string.save))
                }
            }
        })
}