package com.example.staysafe63

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.staysafe63.model.entities.User
import com.example.staysafe63.viewmodel.entitySpecificViewmodel.UserViewModel

@Composable
fun UserScreen(
    userViewModel: UserViewModel = viewModel(),
    navController: NavController
) {
    val userId = SessionManager.loggedInUserId ?: return
    val username = SessionManager.loggedInUsername ?: "Unknown"

    var userList by remember { mutableStateOf(listOf<User>()) }
    var showDeleteConfirm by remember { mutableStateOf(false) }

    // Load users
    LaunchedEffect(Unit) {
        userList = userViewModel.loadAllItems()
    }

    val currentUser = userList.find { it.UserID == userId }

    currentUser?.let { user ->
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Welcome, $username", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(24.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Name: ${user.UserFirstname} ${user.UserLastname}")
                Text("Phone: ${user.UserPhone}")
                Text("Username: ${user.UserUsername}")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = {
                    navController.navigate("contact_screen")
                }) {
                    Text("Manage Contacts")
                }

                Button(onClick = {
                    navController.navigate("activity_screen")
                }) {
                    Text("Plan Activity")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(onClick = {
                    navController.navigate("edit_user_screen/${user.UserID}")
                }) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit User")
                }

                IconButton(onClick = {
                    showDeleteConfirm = true
                }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete User")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Logout Button
            Button(
                onClick = {
                    SessionManager.loggedInUserId = null
                    SessionManager.loggedInUsername = null
                    navController.navigate("login_screen") {
                        popUpTo("user_screen") { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Logout", color = MaterialTheme.colorScheme.onError)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { showDeleteConfirm = true },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Delete Profile", color = MaterialTheme.colorScheme.onError)
            }

            // Delete Confirm Dialog
            if (showDeleteConfirm) {
                AlertDialog(
                    onDismissRequest = { showDeleteConfirm = false },
                    title = { Text("Delete Account") },
                    text = { Text("Are you sure you want to delete your profile? This cannot be undone.") },
                    confirmButton = {
                        TextButton(onClick = {
                            userViewModel.deleteItem(user)
                            SessionManager.loggedInUserId = null
                            SessionManager.loggedInUsername = null
                            navController.navigate("login_screen") {
                                popUpTo("user_screen") { inclusive = true }
                            }
                        }) {
                            Text("Delete", color = MaterialTheme.colorScheme.error)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDeleteConfirm = false }) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
    } ?: run {
        Text("⚠️ Unable to load your profile.")
    }
}







