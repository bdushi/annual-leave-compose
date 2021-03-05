package com.example.compose.ui.signin

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.compose.R
import com.example.compose.nav.Destinations
import com.example.compose.ui.signup.SignUp

@ExperimentalAnimationApi
@Composable
fun SignIn(authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }
    MaterialTheme {
        NavHost(navController = navController, startDestination = Destinations.SignIn) {
            composable(Destinations.SignIn) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = stringResource(id = R.string.sign_in_title)) }
                        )
                    },
                    content = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            AnimatedVisibility(
                                modifier = Modifier.align(Alignment.Center),
                                visible = authViewModel.loading.value
                            ) {
                                CircularProgressIndicator()
                            }

                            AnimatedVisibility(
                                visible = !authViewModel.loading.value
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(24.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    TextField(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth(),
                                        value = username,
                                        onValueChange = {
                                            username = it
                                        },
                                        label = { Text(text = stringResource(id = R.string.username)) },
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Text,
                                            imeAction = ImeAction.Next,
                                        ),
                                        keyboardActions = KeyboardActions {
                                            this.defaultKeyboardAction(ImeAction.Next)
                                        },
                                        isError = username.isBlank() or password.isEmpty()
                                    )
                                    TextField(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth(),
                                        value = password,
                                        onValueChange = {
                                            password = it
                                        },
                                        label = { Text(text = stringResource(id = R.string.password)) },
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Password,
                                            imeAction = ImeAction.Done,
                                        ),
                                        isError = password.isBlank() or password.isEmpty(),
                                        keyboardActions = KeyboardActions {
                                            // if (imeAction == ImeAction.Done)
                                            // softwareKeyboardController.hideSoftwareKeyboard()
                                            this.defaultKeyboardAction(ImeAction.Done)
                                            authViewModel
                                                .login(
                                                    username = username,
                                                    password = password
                                                )
                                        },
                                        visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                                        trailingIcon = {
                                            IconButton(onClick = {
                                                passwordVisibility = !passwordVisibility
                                            }) {
                                                Icon(
                                                    imageVector = if(passwordVisibility) ImageVector.vectorResource(id = R.drawable.ic_baseline_visibility) else ImageVector.vectorResource(id = R.drawable.ic_baseline_visibility_off),
                                                    contentDescription = "Show Password"
                                                )
                                            }
                                        },
                                        placeholder = {

                                        }
                                    )
                                    Button(
                                        enabled = username.isNotEmpty() && password.isNotEmpty(),
                                        modifier = Modifier
                                            .padding(8.dp),
                                        onClick = {
                                            authViewModel.login(
                                                username = username,
                                                password = password
                                            )
                                        }
                                    ) {
                                        Text(text = stringResource(id = R.string.sign_in))
                                    }
                                    Button(
                                        onClick = {
                                            navController.navigate(Destinations.SignUp)
                                        }
                                    ) {
                                        Text(text = stringResource(id = R.string.create_new_account))
                                    }
                                }
                            }
                        }
                    }
                )
            }
            composable(Destinations.SignUp) {
                SignUp(navigateUp = {
                    navController.popBackStack()
                })
            }
        }
    }
}