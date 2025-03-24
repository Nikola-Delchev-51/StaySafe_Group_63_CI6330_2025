package com.example.staysafe63

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

data class Status(
    val statusID: String,
    val statusName: String,
    val statusOrder: Int
)

@Composable
fun StatusScreen() {
    var statusName by remember { mutableStateOf("") }
    var statusOrder by remember { mutableStateOf("") }

    val statusList = remember { mutableStateListOf<Status>() }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Add New Status", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = statusName,
            onValueChange = { statusName = it },
            label = { Text("Status Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = statusOrder,
            onValueChange = { statusOrder = it },
            label = { Text("Status Order (e.g., 1, 2, 3)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            val newStatus = Status(
                statusID = System.currentTimeMillis().toString(),
                statusName = statusName,
                statusOrder = statusOrder.toIntOrNull() ?: 0
            )

            statusList.add(newStatus)

            // Placeholder backend call - to be connected later
            // createStatus(newStatus)

            statusName = ""
            statusOrder = ""
        }) {
            Text("Add Status")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Status List", style = MaterialTheme.typography.headlineSmall)

        LazyColumn {
            items(statusList) { status ->
                Text("${status.statusOrder}: ${status.statusName}")
                HorizontalDivider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StatusScreenPreview() {
    StatusScreen()
}
