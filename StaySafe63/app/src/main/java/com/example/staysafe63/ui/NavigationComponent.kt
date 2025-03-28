package com.example.staysafe63.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.staysafe63.ui.screens.ActivityScreen
import com.example.staysafe63.ui.screens.ContactScreen
import com.example.staysafe63.ui.screens.StatusScreen
import com.example.staysafe63.ui.screens.StepTrackingScreen
import com.example.staysafe63.ui.screens.UserEditScreen
import com.example.staysafe63.ui.screens.UserLoginScreen
import com.example.staysafe63.ui.screens.UserRegistrationScreen
import com.example.staysafe63.ui.screens.UserScreen


/**
* @author K2128078
*
* */
@Composable
fun NavigationComponent() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login_screen") {

        // ENTRY POINTS
        composable("login_screen") {
            UserLoginScreen(navController = navController)
        }

        composable("register_screen") {
            UserRegistrationScreen(navController = navController)
        }

        // APP SCREENS with drawer & Top bar
        composable("user_screen") {
            UserScreen(navController = navController)
        }

        composable("step_tracking_screen") {
            AppScaffold(screenTitle = "Step Tracking", navController = navController) {
                StepTrackingScreen()
            }
        }

        composable(
            route = "edit_user_screen/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            UserEditScreen(userId = userId, navController = navController)
        }

        composable("contact_screen") {
            ContactScreen(navController = navController)
        }

        composable("activity_screen") {
            ActivityScreen(navController = navController)
        }

        composable("status_screen") {
            StatusScreen(navController = navController)
        }
    }
}


