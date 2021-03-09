package com.example.compose.ui.signup

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun SignUp(
    navigateUp: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                title = { Text(text = "New Account") },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "Back Button")
                    }
                },
                actions = {
                    Button(
                        onClick = {
                            navigateUp()
                        }
                    ) {
                        Text("Save")
                    }
                }
            )
        },
        content = {
            Text(text = "Test")
        })
}