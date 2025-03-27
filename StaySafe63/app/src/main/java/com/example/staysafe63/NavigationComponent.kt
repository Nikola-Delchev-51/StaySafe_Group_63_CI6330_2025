package com.example.staysafe63

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument

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


