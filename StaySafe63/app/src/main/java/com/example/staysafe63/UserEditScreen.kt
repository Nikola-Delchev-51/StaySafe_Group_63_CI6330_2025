package com.example.staysafe63

import androidx.compose.foundation.layout.*
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
fun UserEditScreen(
    userId: Int,
    userViewModel: UserViewModel = viewModel(),
    navController: NavController? = null
) {
    var user by remember { mutableStateOf<User?>(null) }

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
        Text("Edit User", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

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
                    navController?.popBackStack()
                }
            }
        }) {
            Text("Save Changes")
        }
    }
}
