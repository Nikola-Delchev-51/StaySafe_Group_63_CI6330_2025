package com.example.staysafe63.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.staysafe63.viewmodel.entitySpecificViewmodel.UserViewModel
import kotlinx.coroutines.launch
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.example.staysafe63.ui.SessionManager

/**
* @author K2128078
*
* */
@Composable
fun UserLoginScreen(
    navController: NavController,
    userViewModel: UserViewModel = viewModel()
) {
    // State variables
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // App Title
        Text(
            text = "StaySafe",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            textAlign = TextAlign.Center
        )

        // Login Section Title
        Text(
            text = "Login",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        // Username input
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Password input
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Login button
        Button(onClick = {
            scope.launch {
                val users = userViewModel.loadAllItems()
                val matchedUser = users.find {
                    it.UserUsername == username.lowercase() && it.UserPassword == password
                }

                if (matchedUser != null) {
                    // Save user session
                    SessionManager.loggedInUserId = matchedUser.UserID
                    SessionManager.loggedInUsername = matchedUser.UserUsername

                    errorMessage = null
                    navController.navigate("user_screen")
                } else {
                    errorMessage = "Invalid username or password"
                }
            }
        }) {
            Text("Login")
        }

        // Navigate to registration screen
        TextButton(onClick = {
            navController.navigate("register_screen")
        }) {
            Text("Don’t have an account? Register")
        }

        // Display error message if present
        errorMessage?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }
    }
}



