package com.example.staysafe63

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.staysafe63.model.entities.User
import com.example.staysafe63.viewmodel.entitySpecificViewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun UserScreen(userViewModel: UserViewModel = viewModel(), navController: NavController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var userList by remember { mutableStateOf(listOf<User>()) }
    val scope = rememberCoroutineScope()

    // Load from DB
    LaunchedEffect(Unit) {
        userList = userViewModel.loadAllItems()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            userViewModel.createUser(
                firstname = firstName,
                lastname = lastName,
                phone = phone,
                username = username,
                password = password,
                latitude = 0.0,
                longitude = 0.0,
                timestamp = System.currentTimeMillis()
            )

            scope.launch {
                userList = userViewModel.loadAllItems()
            }

            firstName = ""
            lastName = ""
            phone = ""
            username = ""
            password = ""
        }) {
            Text("Add User")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Users:", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(userList) { user ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "${user.UserFirstname} ${user.UserLastname}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            "Username: ${user.UserUsername}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Row {
                        IconButton(onClick = {
                            navController.navigate("edit_user_screen/${user.UserID}")
                        }) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit User")
                        }

                        IconButton(onClick = {
                            userViewModel.deleteItem(user)
                            scope.launch {
                                userList = userViewModel.loadAllItems()
                            }
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete User")
                        }
                    }
                }
            }
        }
    }
}




