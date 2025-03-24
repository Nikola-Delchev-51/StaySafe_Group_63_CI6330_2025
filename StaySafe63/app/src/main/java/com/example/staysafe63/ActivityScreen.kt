package com.example.staysafe63

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

data class Activity(
    val activityID: String,
    val activityName: String,
    val activityUserID: String,
    val activityDescription: String,
    val activityFromID: String,
    val activityFromName: String,
    val activityToID: String,
    val activityToName: String,
    val activityLeave: String,
    val activityArrive: String,
    val activityStatusID: String,
    val activityStatusName: String
)

@Composable
fun ActivityScreen() {
    var activityName by remember { mutableStateOf("") }
    var activityDescription by remember { mutableStateOf("") }
    var fromName by remember { mutableStateOf("") }
    var toName by remember { mutableStateOf("") }
    var leaveTime by remember { mutableStateOf("") }
    var arriveTime by remember { mutableStateOf("") }
    var statusName by remember { mutableStateOf("") }

    val activityList = remember { mutableStateListOf<Activity>() }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Add New Activity", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = activityName,
            onValueChange = { activityName = it },
            label = { Text("Activity Name") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = activityDescription,
            onValueChange = { activityDescription = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = fromName,
            onValueChange = { fromName = it },
            label = { Text("From (Location Name)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = toName,
            onValueChange = { toName = it },
            label = { Text("To (Location Name)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = leaveTime,
            onValueChange = { leaveTime = it },
            label = { Text("Leave Time") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = arriveTime,
            onValueChange = { arriveTime = it },
            label = { Text("Arrive Time") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = statusName,
            onValueChange = { statusName = it },
            label = { Text("Status (Planned/Completed)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            val newActivity = Activity(
                activityID = System.currentTimeMillis().toString(),
                activityName = activityName,
                activityUserID = "dummyUserID", // Replace with actual user ID later
                activityDescription = activityDescription,
                activityFromID = "fromID", // Replace with actual location ID
                activityFromName = fromName,
                activityToID = "toID", // Replace with actual location ID
                activityToName = toName,
                activityLeave = leaveTime,
                activityArrive = arriveTime,
                activityStatusID = "statusID", // Replace with actual status ID
                activityStatusName = statusName
            )

            activityList.add(newActivity)

            // Backend call - will be used later
            // createActivity(newActivity)

            activityName = ""
            activityDescription = ""
            fromName = ""
            toName = ""
            leaveTime = ""
            arriveTime = ""
            statusName = ""
        }) {
            Text("Add Activity")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Planned Activities", style = MaterialTheme.typography.headlineSmall)

        LazyColumn {
            items(activityList) { activity ->
                Text("Activity: ${activity.activityName}")
                Text("From: ${activity.activityFromName} To: ${activity.activityToName}")
                Text("Status: ${activity.activityStatusName}")
                HorizontalDivider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityScreenPreview() {
    ActivityScreen()
}