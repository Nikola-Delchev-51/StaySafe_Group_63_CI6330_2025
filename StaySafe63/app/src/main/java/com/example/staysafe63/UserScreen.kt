package com.example.staysafe63

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.staysafe63.viewmodel.entitySpecificViewmodel.UserViewModel
import com.example.staysafe63.model.entities.User
import kotlinx.coroutines.launch

@Composable
fun UserScreen(userViewModel: UserViewModel = viewModel()) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var userList by remember { mutableStateOf(listOf<User>()) }

    val scope = rememberCoroutineScope()

    // Track if editing and which user
    var editingUser by remember { mutableStateOf<User?>(null) }

    // Load users on screen load
    LaunchedEffect(Unit) {
        userList = userViewModel.loadAllItems()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // Input Fields
        OutlinedTextField(value = firstName, onValueChange = { firstName = it }, label = { Text("First Name") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = lastName, onValueChange = { lastName = it }, label = { Text("Last Name") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("Username") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))

        // Submit / Update Button
        Button(onClick = {
            if (editingUser == null) {
                // ADD USER
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
            } else {
                // UPDATE USER
                val updatedUser = editingUser!!.copy(
                    UserFirstname = firstName,
                    UserLastname = lastName,
                    UserPhone = phone,
                    UserUsername = username,
                    UserPassword = password,
                    UserLatitude = 0.0,
                    UserLongitude = 0.0,
                    UserTimestamp = System.currentTimeMillis()
                )
                userViewModel.updateItem(editingUser!!, updatedUser)
                editingUser = null
            }

            // Refresh list
            scope.launch {
                userList = userViewModel.loadAllItems()
            }

            // Clear form
            firstName = ""
            lastName = ""
            phone = ""
            username = ""
            password = ""
        }) {
            Text(if (editingUser == null) "Add User" else "Update User")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Users:", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(userList) { user ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable {
                            // Fill form for editing
                            editingUser = user
                            firstName = user.UserFirstname
                            lastName = user.UserLastname
                            phone = user.UserPhone
                            username = user.UserUsername
                            password = user.UserPassword
                        },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("${user.UserFirstname} ${user.UserLastname}", style = MaterialTheme.typography.bodyMedium)
                        Text("Username: ${user.UserUsername}", style = MaterialTheme.typography.bodySmall)
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