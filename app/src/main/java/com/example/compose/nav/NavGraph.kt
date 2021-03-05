package com.example.compose.nav

import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.example.compose.nav.Destinations.LeaveDetail
import com.example.compose.nav.Destinations.AddLeave
import com.example.compose.nav.Destinations.SignUp

object Destinations {
    const val Home = "home"
    const val AddLeave = "addLeave"
    const val SignIn = "signIn"
    const val SignUp = "signUp"
    const val LeaveDetail = "leaveDetail"

    object LeaveDetailArgs {
        const val LeaveId = "leaveId"
    }
}

class Actions(navController: NavHostController) {
    val openLeave: (Int) -> Unit = { taskId ->
        navController.navigate("$LeaveDetail/$taskId")
    }
    val addLeave: () -> Unit = {
        navController.navigate(AddLeave)
    }
    val navigateUp: () -> Unit = {
        navController.popBackStack()
    }
}