package com.example.staysafe63.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.staysafe63.model.entities.User
import com.example.staysafe63.ui.AppScaffold
import com.example.staysafe63.viewmodel.entitySpecificViewmodel.UserViewModel
import kotlinx.coroutines.launch


/**
* @author K2128078
*
* */
@Composable
fun UserEditScreen(
    userId: Int,
    userViewModel: UserViewModel = viewModel(),
    navController: NavController
) {
    AppScaffold(
        screenTitle = "Edit Profile",
        navController = navController
    ) {
        // State for current user
        var user by remember { mutableStateOf<User?>(null) }

        // Form fields
        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf("") }
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        val scope = rememberCoroutineScope()

        // Load user info
        LaunchedEffect(userId) {
            val users = userViewModel.loadAllItems()
            user = users.find { it.UserID == userId }
            user?.let {
                firstName = it.UserFirstname
                lastName = it.UserLastname
                phone = it.UserPhone
                username = it.UserUsername
                password = it.UserPassword
            }
        }

        Column(modifier = Modifier.padding(16.dp)) {

            // Title
            Text("Edit User", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))

            // Form fields
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Save changes button
            Button(onClick = {
                user?.let {
                    val updatedUser = it.copy(
                        UserFirstname = firstName,
                        UserLastname = lastName,
                        UserPhone = phone,
                        UserUsername = username,
                        UserPassword = password,
                        UserTimestamp = System.currentTimeMillis()
                    )
                    userViewModel.updateUser(user!!, updatedUser)
                    scope.launch {
                        navController.popBackStack()
                    }
                }
            }) {
                Text("Save Changes")
            }

        }
    }
}

