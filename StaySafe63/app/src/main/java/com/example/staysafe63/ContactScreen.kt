package com.example.staysafe63

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

data class Contact(
    val contactID: String,
    val contactUserID: String,
    val contactContactID: String,
    val contactLabel: String,
    val contactDateCreated: String
)

@Composable
fun ContactScreen() {
    var contactLabel by remember { mutableStateOf("") }

    val contactList = remember { mutableStateListOf<Contact>() }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Add Contact", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = contactLabel,
            onValueChange = { contactLabel = it },
            label = { Text("Contact Label") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            val newContact = Contact(
                contactID = System.currentTimeMillis().toString(),
                contactUserID = "User123",  // Dummy for now
                contactContactID = "ContactXYZ",  // Dummy too
                contactLabel = contactLabel,
                contactDateCreated = "2025-03-23"
            )

            contactList.add(newContact)

            // Backend call placeholder
            // createContact(newContact)

            contactLabel = ""
        }) {
            Text("Add Contact")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Contacts List", style = MaterialTheme.typography.headlineSmall)

        LazyColumn {
            items(contactList) { contact ->
                Text("Label: ${contact.contactLabel} | ID: ${contact.contactID}")
                HorizontalDivider()

                // Backend placeholders
                // updateContact(contact)
                // deleteContact(contact.contactID)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactScreenPreview() {
    ContactScreen()
}