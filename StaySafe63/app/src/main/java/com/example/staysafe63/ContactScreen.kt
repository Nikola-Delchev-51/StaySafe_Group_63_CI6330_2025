package com.example.staysafe63

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
import com.example.staysafe63.viewmodel.entitySpecificViewmodel.ContactViewModel
import com.example.staysafe63.viewmodel.entitySpecificViewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun ContactScreen(
    navController: NavController? = null,
    contactViewModel: ContactViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel()
) {
    val userId = 1

    var label by remember { mutableStateOf("") }
    var selectedUserId by remember { mutableStateOf<Int?>(null) }
    var editingContact by remember { mutableStateOf<Contact?>(null) }

    val contactList = remember { mutableStateListOf<Contact>() }
    var userList by remember { mutableStateOf(listOf<User>()) }

    val scope = rememberCoroutineScope()

    // Load contacts and users
    LaunchedEffect(Unit) {
        contactList.clear()
        contactList.addAll(contactViewModel.loadAllItems())
        userList = userViewModel.loadAllItems()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Add Emergency Contact", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { navController?.popBackStack() }) {
            Text("⬅ Back", style = MaterialTheme.typography.bodyLarge)
        }

        Text("Select User to Add:")
        DropdownMenuWithUsers(userList, selectedUserId) { selectedUserId = it }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = label,
            onValueChange = { label = it },
            label = { Text("Label (e.g. Mum, Friend)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            enabled = selectedUserId != null && label.isNotBlank(),
            onClick = {
                if (editingContact != null) {
                    val updated = editingContact!!.copy(
                        ContactContactID = selectedUserId!!,
                        ContactLabel = label,
                        ContactDateCreated = System.currentTimeMillis()
                    )
                    contactViewModel.updateContact(editingContact!!, updated)
                    editingContact = null
                } else {
                    contactViewModel.createContact(
                        userId = userId,
                        contactId = selectedUserId!!,
                        label = label,
                        dateCreated = System.currentTimeMillis()
                    )
                }

                scope.launch {
                    val updatedList = contactViewModel.loadAllItems()
                    contactList.clear()
                    contactList.addAll(updatedList)
                }

                label = ""
                selectedUserId = null
            }
        ) {
            Text(if (editingContact != null) "Save Changes" else "Add Contact")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Your Contacts", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(contactList.filter { it.ContactUserID == userId }) { contact ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        val contactUser = userList.find { it.UserID == contact.ContactContactID }
                        Text("👤 Name: ${contactUser?.UserFirstname} ${contactUser?.UserLastname}")
                        Text("📱 Phone: ${contactUser?.UserPhone}")
                        Text("🏷️ Label: ${contact.ContactLabel}")
                    }

                    Row {
                        IconButton(onClick = {
                            editingContact = contact
                            selectedUserId = contact.ContactContactID
                            label = contact.ContactLabel
                        }) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit Contact")
                        }

                        IconButton(onClick = {
                            contactViewModel.deleteItem(contact)
                            scope.launch {
                                val updatedList = contactViewModel.loadAllItems()
                                contactList.clear()
                                contactList.addAll(updatedList)
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


