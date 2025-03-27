package com.example.staysafe63.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.staysafe63.model.entities.Contact
import com.example.staysafe63.model.entities.Status
import com.example.staysafe63.ui.AppScaffold
import com.example.staysafe63.ui.SessionManager
import com.example.staysafe63.viewmodel.entitySpecificViewmodel.ContactViewModel
import com.example.staysafe63.viewmodel.entitySpecificViewmodel.StatusViewModel
import kotlinx.coroutines.launch


/*
* @author K2128078
*
* */
@Composable
fun StatusScreen(
    navController: NavController,
    statusViewModel: StatusViewModel = viewModel(),
    contactViewModel: ContactViewModel = viewModel()
) {
    AppScaffold(
        screenTitle = "Manage Statuses",
        navController = navController
    ) {
        // session
        val context = LocalContext.current
        val userId = SessionManager.loggedInUserId ?: return@AppScaffold
        val username = SessionManager.loggedInUsername ?: "Unknown"

        // State variables
        var statusName by remember { mutableStateOf("") }
        var statusList by remember { mutableStateOf(listOf<Status>()) }
        var contactList by remember { mutableStateOf(listOf<Contact>()) }

        val scope = rememberCoroutineScope()

        // Load data
        LaunchedEffect(Unit) {
            statusList = statusViewModel.loadAllItems()
            contactList = contactViewModel.loadAllItems()
            println("✅ Logged-in user: $username (ID: $userId)")
            println("✅ Contacts loaded: ${contactList.size}")
        }

        Column(modifier = Modifier.padding(16.dp)) {

            // Back navigation
            TextButton(onClick = { navController.popBackStack() }) {
                Text("⬅ Back", style = MaterialTheme.typography.bodyLarge)
            }

            // Status input field
            OutlinedTextField(
                value = statusName,
                onValueChange = { statusName = it },
                label = { Text("Status Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Add status button
            Button(
                onClick = {
                    // Create the status
                    statusViewModel.createStatus(
                        name = statusName,
                        order = 0
                    )

                    // Notify emergency contacts
                    val userContacts = contactList.filter { it.ContactUserID == userId }

                    if (userContacts.isEmpty()) {
                        Toast.makeText(
                            context,
                            "⚠️ No emergency contacts to notify.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        userContacts.forEach { contact ->
                            Toast.makeText(
                                context,
                                "📢 Notified ${contact.ContactLabel}: $username updated status to '$statusName'",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    // Refresh UI
                    scope.launch {
                        statusList = statusViewModel.loadAllItems()
                        contactList = contactViewModel.loadAllItems()
                    }

                    // Reset input
                    statusName = ""
                },
                enabled = statusName.isNotBlank()
            ) {
                Text("Add Status")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Existing status list
            Text("Existing Statuses", style = MaterialTheme.typography.titleMedium)

            LazyColumn {
                items(statusList) { status ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = status.StatusName, style = MaterialTheme.typography.bodyLarge)

                        // Delete button
                        IconButton(onClick = {
                            statusViewModel.deleteItem(status)
                            scope.launch {
                                statusList = statusViewModel.loadAllItems()
                            }
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete Status")
                        }
                    }
                }
            }
        }
    }
}

