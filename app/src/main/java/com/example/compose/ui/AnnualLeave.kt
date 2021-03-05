package com.example.compose.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.nav.Actions
import com.example.compose.nav.Destinations
import com.example.compose.ui.addleave.AddLeave
import com.example.compose.ui.home.Home
import com.example.compose.ui.home.LeaveViewModel
import com.example.compose.ui.signin.AuthViewModel

@Composable
fun AnnualLeave(
    datePicker: () -> Unit,
    leaveViewModel: LeaveViewModel,
    authViewModel: AuthViewModel
) {
    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }
    MaterialTheme {
        NavHost(navController = navController, startDestination = Destinations.Home) {
            composable(Destinations.Home) {
                Home(
                    leaveViewModel = leaveViewModel,
                    authViewModel = authViewModel,
                    addLeave = actions.addLeave
                )
            }
            composable(Destinations.AddLeave) {
                AddLeave(
                    datePicker = datePicker,
                    success = actions.navigateUp,
                    navigateUp = actions.navigateUp,
                    leaveViewModel = leaveViewModel
                )
            }
        }
    }
}