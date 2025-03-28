package com.example.staysafe63.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.staysafe63.model.entities.Contact
import com.example.staysafe63.model.entities.User
import com.example.staysafe63.ui.AppScaffold
import com.example.staysafe63.ui.SessionManager
import com.example.staysafe63.viewmodel.entitySpecificViewmodel.ContactViewModel
import com.example.staysafe63.viewmodel.entitySpecificViewmodel.UserViewModel
import kotlinx.coroutines.launch


/**
* @author K2128078
*
* */
@Composable
fun ContactScreen(
    navController: NavController? = null,
    contactViewModel: ContactViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel()
) {
    // Session and user info
    val userId = SessionManager.loggedInUserId ?: return
    val username = SessionManager.loggedInUsername ?: "Unknown"

    // Form state
    var label by remember { mutableStateOf("") }
    var selectedUserId by remember { mutableStateOf<Int?>(null) }

    // Data lists
    var contactList by remember { mutableStateOf(listOf<Contact>()) }
    var userList by remember { mutableStateOf(listOf<User>()) }

    // Edit state
    var editingContact by remember { mutableStateOf<Contact?>(null) }

    val scope = rememberCoroutineScope()

    // data load
    LaunchedEffect(Unit) {
        contactList = contactViewModel.loadAllItems()
        userList = userViewModel.loadAllItems()
        println("✅ Logged-in user: $username (ID: $userId)")
    }

    AppScaffold(
        screenTitle = "Add Emergency Contact",
        navController = navController!!
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // Back navigation
            TextButton(onClick = { navController.popBackStack() }) {
                Text("⬅ Back", style = MaterialTheme.typography.bodyLarge)
            }

            // Dropdown for selecting user
            Text("Select User to Add:")
            DropdownMenuWithUsers(userList, selectedUserId) { selectedUserId = it }

            Spacer(modifier = Modifier.height(8.dp))

            // Label input
            OutlinedTextField(
                value = label,
                onValueChange = { label = it },
                label = { Text("Label (e.g. Mum, Friend)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Add/Update button
            Button(
                enabled = selectedUserId != null && label.isNotBlank(),
                onClick = {
                    if (editingContact == null) {
                        // Add new contact
                        val newContact = Contact(
                            ContactUserID = userId,
                            ContactContactID = selectedUserId!!,
                            ContactLabel = label,
                            ContactDateCreated = System.currentTimeMillis()
                        )
                        contactViewModel.createItem(newContact)
                    } else {
                        // Update contact
                        val updated = editingContact!!.copy(
                            ContactContactID = selectedUserId!!,
                            ContactLabel = label
                        )
                        contactViewModel.updateContact(editingContact!!, updated)
                    }

                    // Reload list
                    scope.launch {
                        contactList = contactViewModel.loadAllItems()
                    }

                    // Reset form
                    label = ""
                    selectedUserId = null
                    editingContact = null
                }
            ) {
                Text(if (editingContact == null) "Add Contact" else "Update Contact")
            }

            // Cancel edit button
            if (editingContact != null) {
                TextButton(onClick = {
                    editingContact = null
                    label = ""
                    selectedUserId = null
                }) {
                    Text("Cancel")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Contact list
            Text("Your Contacts", style = MaterialTheme.typography.titleMedium)

            LazyColumn {
                items(contactList.filter { it.ContactUserID == userId }) { contact ->
                    val contactUser = userList.find { it.UserID == contact.ContactContactID }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Name: ${contactUser?.UserFirstname} ${contactUser?.UserLastname}")
                            Text("Phone: ${contactUser?.UserPhone}")
                            Text("Label: ${contact.ContactLabel}")
                        }

                        Row {
                            // Edit button
                            IconButton(onClick = {
                                editingContact = contact
                                label = contact.ContactLabel
                                selectedUserId = contact.ContactContactID
                            }) {
                                Icon(Icons.Default.Edit, contentDescription = "Edit Contact")
                            }

                            // Delete button
                            IconButton(onClick = {
                                contactViewModel.deleteItem(contact)
                                scope.launch {
                                    contactList = contactViewModel.loadAllItems()
                                }
                            }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete Contact")
                            }
                        }
                    }
                }
            }
        }
    }
}


/**
* @author K2128078
*
* Dropdown for selecting users
* */
@Composable
fun DropdownMenuWithUsers(
    userList: List<User>,
    selectedUserId: Int?,
    onUserSelected: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val selectedUser = userList.find { it.UserID == selectedUserId }

    Box {
        OutlinedTextField(
            value = selectedUser?.UserUsername ?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text("Select Contact") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Select Contact")
                }
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            userList.forEach { user ->
                DropdownMenuItem(
                    text = { Text(user.UserUsername) },
                    onClick = {
                        onUserSelected(user.UserID)
                        expanded = false
                    }
                )
            }
        }
    }
}



