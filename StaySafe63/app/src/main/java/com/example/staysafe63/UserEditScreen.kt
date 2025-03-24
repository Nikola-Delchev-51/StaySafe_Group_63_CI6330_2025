package com.example.staysafe63

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UserEditScreen() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var editMessage by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Edit User Details", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("New Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            // Placeholder backend call
            // updateUser(firstName, lastName, phone, password)

            editMessage = "UpdateUser function called (placeholder)"
        }) {
            Text("Save Changes")
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(editMessage)
    }
}

@Preview(showBackground = true)
@Composable
fun UserEditScreenPreview() {
    UserEditScreen()
}
