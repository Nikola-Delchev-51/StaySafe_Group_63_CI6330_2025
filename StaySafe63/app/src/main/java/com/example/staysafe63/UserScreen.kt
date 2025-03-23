package com.example.staysafe63

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.staysafe63.ui.theme.StaySafe63Theme

// --- User Data Class ---
data class User(
    val userID: String,
    val userFirstname: String,
    val userLastname: String,
    val userPhone: String,
    val userUsername: String,
    val userPassword: String,
    val userLatitude: Double,
    val userLongitude: Double,
    val userTimestamp: String
)

// --- Composable Function ---
@Composable
fun UserScreen() {
    // Input states
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Dummy user list
    var userList by remember { mutableStateOf(listOf<User>()) }

    Column(modifier = Modifier.padding(16.dp)) {
        // --- Form Inputs ---
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

        // --- Submit Button ---
        Button(onClick = {
            val newUser = User(
                userID = System.currentTimeMillis().toString(),
                userFirstname = firstName,
                userLastname = lastName,
                userPhone = phone,
                userUsername = username,
                userPassword = password,
                userLatitude = 0.0,  // Placeholder
                userLongitude = 0.0,
                userTimestamp = "2025-03-23T00:00:00Z" // Placeholder
            )

            userList = userList + newUser

            // --- Backend Function (commented) ---
            // createUser(newUser)

            // Clear form
            firstName = ""
            lastName = ""
            phone = ""
            username = ""
            password = ""
        }) {
            Text("Add User")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- User List ---
        Text("Users:", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(userList) { user ->
                Text("- ${user.userFirstname} ${user.userLastname}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

/*
// --- Backend Functions (Commented Out) ---
fun createUser(user: User) {
    // TODO: Implement API call
}

fun updateUser(user: User) {
    // TODO: Implement API call
}

fun deleteUser(userID: String) {
    // TODO: Implement API call
}
*/

@Preview(showBackground = true)
@Composable
fun UserScreenPreview() {
    StaySafe63Theme {
        UserScreen()
    }
}