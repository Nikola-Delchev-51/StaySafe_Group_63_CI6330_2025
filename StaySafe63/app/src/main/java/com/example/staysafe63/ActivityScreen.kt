package com.example.staysafe63

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.staysafe63.model.entities.Activity
import com.example.staysafe63.model.entities.Contact
import com.example.staysafe63.model.entities.Status
import com.example.staysafe63.viewmodel.entitySpecificViewmodel.ActivityViewModel
import com.example.staysafe63.viewmodel.entitySpecificViewmodel.ContactViewModel
import com.example.staysafe63.viewmodel.entitySpecificViewmodel.StatusViewModel
import kotlinx.coroutines.launch


/*
* @author K2128078
*
* */
@Composable
fun ActivityScreen(
    navController: NavController,
    activityViewModel: ActivityViewModel = viewModel(),
    statusViewModel: StatusViewModel = viewModel(),
    contactViewModel: ContactViewModel = viewModel()
) {
    AppScaffold(screenTitle = "Plan Activity", navController = navController) {

        val context = LocalContext.current
        val userId = SessionManager.loggedInUserId ?: return@AppScaffold
        val username = SessionManager.loggedInUsername ?: "Unknown"

        // Form field state
        var name by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }
        var fromName by remember { mutableStateOf("") }
        var toName by remember { mutableStateOf("") }
        var arriveTime by remember { mutableStateOf("") }
        val leaveTime = System.currentTimeMillis()

        // Data lists and selected items
        var activityList by remember { mutableStateOf(listOf<Activity>()) }
        var statusList by remember { mutableStateOf(listOf<Status>()) }
        var contactList by remember { mutableStateOf(listOf<Contact>()) }
        var selectedStatusId by remember { mutableStateOf<Int?>(null) }
        var editingActivity by remember { mutableStateOf<Activity?>(null) }

        val scope = rememberCoroutineScope()

        // Load data
        LaunchedEffect(Unit) {
            activityList = activityViewModel.loadAllItems()
            statusList = statusViewModel.loadAllItems()
            contactList = contactViewModel.loadAllItems()
        }

        Column(modifier = Modifier.padding(16.dp)) {

            // Back navigation
            TextButton(onClick = { navController.popBackStack() }) {
                Text("⬅ Back", style = MaterialTheme.typography.bodyLarge)
            }

            // Input fields
            OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Activity Name") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = fromName, onValueChange = { fromName = it }, label = { Text("From Location") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = toName, onValueChange = { toName = it }, label = { Text("To Location") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = arriveTime, onValueChange = { arriveTime = it }, label = { Text("Arrival Time") }, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(8.dp))

            // Status dropdown
            Text("Select Status:")
            DropdownMenuForStatuses(statusList, selectedStatusId) { selectedStatusId = it }

            Spacer(modifier = Modifier.height(8.dp))

            // Navigate to manage statuses
            Button(onClick = { navController.navigate("status_screen") }) {
                Text("Manage Statuses")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Add or Update Activity button
            Button(
                onClick = {
                    val selectedStatus = statusList.find { it.StatusID == selectedStatusId }

                    if (editingActivity == null) {
                        // Create new activity
                        activityViewModel.createActivity(
                            name = name,
                            userId = userId,
                            username = username,
                            description = description,
                            fromId = 0,
                            fromName = fromName,
                            leave = leaveTime,
                            toId = 0,
                            toName = toName,
                            arrive = arriveTime,
                            statusId = selectedStatusId ?: 0,
                            statusName = selectedStatus?.StatusName ?: "Unknown"
                        )
                    } else {
                        // Update existing activity
                        val updated = editingActivity!!.copy(
                            ActivityName = name,
                            ActivityDescription = description,
                            ActivityFromID = 0,
                            ActivityFromName = fromName,
                            ActivityLeave = leaveTime,
                            ActivityToID = 0,
                            ActivityToName = toName,
                            ActivityArrive = arriveTime,
                            ActivityStatusID = selectedStatusId ?: 0,
                            ActivityStatusName = selectedStatus?.StatusName ?: "Unknown"
                        )

                        activityViewModel.updateActivity(editingActivity!!, updated)

                        // Notify contacts
                        val userContacts = contactList.filter { it.ContactUserID == userId }

                        if (userContacts.isEmpty()) {
                            Toast.makeText(context, "No emergency contacts to notify.", Toast.LENGTH_SHORT).show()
                        } else {
                            userContacts.forEach { contact ->
                                Toast.makeText(
                                    context,
                                    "Notified ${contact.ContactLabel}: $username updated activity status to '${selectedStatus?.StatusName}'",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }

                    scope.launch {
                        activityList = activityViewModel.loadAllItems()
                    }

                    // Reset form
                    name = ""
                    description = ""
                    fromName = ""
                    toName = ""
                    arriveTime = ""
                    selectedStatusId = null
                    editingActivity = null
                },
                enabled = name.isNotBlank() && selectedStatusId != null
            ) {
                Text(if (editingActivity == null) "Add Activity" else "Update Activity")
            }

            // Cancel edit button
            if (editingActivity != null) {
                TextButton(onClick = {
                    editingActivity = null
                    name = ""
                    description = ""
                    fromName = ""
                    toName = ""
                    arriveTime = ""
                    selectedStatusId = null
                }) {
                    Text("Cancel")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // List of activities
            Text("Planned Activities", style = MaterialTheme.typography.titleMedium)

            LazyColumn {
                items(activityList.filter { it.ActivityUserID == userId }) { activity ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Activity: ${activity.ActivityName} (${activity.ActivityFromName} ➡ ${activity.ActivityToName})")
                            Text("Description: ${activity.ActivityDescription}")
                            Text("Arrival Time: ${activity.ActivityArrive}")
                            Text("Status: ${activity.ActivityStatusName}")
                        }

                        Row {
                            // Edit button
                            IconButton(onClick = {
                                editingActivity = activity
                                name = activity.ActivityName
                                description = activity.ActivityDescription
                                fromName = activity.ActivityFromName
                                toName = activity.ActivityToName
                                arriveTime = activity.ActivityArrive
                                selectedStatusId = activity.ActivityStatusID
                            }) {
                                Icon(Icons.Default.Edit, contentDescription = "Edit Activity")
                            }

                            // Delete button
                            IconButton(onClick = {
                                scope.launch {
                                    activityViewModel.deleteItem(activity)
                                    activityList = activityViewModel.loadAllItems()
                                }
                            }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete Activity")
                            }
                        }
                    }
                }
            }
        }
    }
}


/*
* @author K2128078
*
* Dropdown menu composable
* */
@Composable
fun DropdownMenuForStatuses(
    statusList: List<Status>,
    selectedStatusId: Int?,
    onStatusSelected: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedStatus = statusList.find { it.StatusID == selectedStatusId }

    Box {
        OutlinedTextField(
            value = selectedStatus?.StatusName ?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text("Select Status") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                }
            }
        )

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            statusList.forEach { status ->
                DropdownMenuItem(
                    text = { Text(status.StatusName) },
                    onClick = {
                        onStatusSelected(status.StatusID)
                        expanded = false
                    }
                )
            }
        }
    }
}

