package com.example.staysafe63.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.staysafe63.viewmodel.entitySpecificViewmodel.UserViewModel
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.*


/**
* @author K2128078
*
* */
@Composable
fun UserRegistrationScreen(
    navController: NavController? = null,
    userViewModel: UserViewModel = viewModel()
) {
    // Form field state
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Register New User",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // First name input
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Last name input
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Phone input
        OutlinedTextField(
            value = phone,
            onValueChange = { input ->
                // Remove whitespace characters from the input
                val filteredInput = input.filterNot { it.isWhitespace() }
                if (filteredInput == input) {
                    phone = input} },
            label = { Text("Phone") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Username input
        OutlinedTextField(
            value = username,
            onValueChange = { input ->
                // Remove whitespace characters from the input
                val filteredInput = input.filterNot { it.isWhitespace() }
                if (filteredInput == input) {
                    username = input} },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Password input
        OutlinedTextField(
            value = password,
            onValueChange = { input ->
                // Remove whitespace characters from the input
                val filteredInput = input.filterNot { it.isWhitespace() }
                if (filteredInput == input) {
                    password = input} },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Register button
        Button(onClick = {
            userViewModel.createUser(
                firstname = firstName,
                lastname = lastName,
                phone = phone,
                username = username.lowercase(),
                password = password,
                latitude = 0.0,
                longitude = 0.0,
                timestamp = System.currentTimeMillis()
            )

            // Reset form fields
            firstName = ""
            lastName = ""
            phone = ""
            username = ""
            password = ""

            // Navigate back to login screen
            navController?.popBackStack()
        }) {
            Text("Register")
        }
    }
}

