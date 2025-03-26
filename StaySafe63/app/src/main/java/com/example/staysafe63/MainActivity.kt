package com.example.staysafe63

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.staysafe63.ui.theme.StaySafe63Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            StaySafe63Theme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "login_screen") {


                    composable("login_screen") {
                        UserLoginScreen(navController = navController)
                    }


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

                    composable("register_screen") {
                        UserRegistrationScreen(navController = navController)
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
        }
    }
}
