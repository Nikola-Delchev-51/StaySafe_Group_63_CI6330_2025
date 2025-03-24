package com.example.staysafe63

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

// Data class for Position
data class Position(
    val positionID: String,
    val positionActivityID: String,
    val positionActivityName: String,
    val positionLatitude: Double,
    val positionLongitude: Double,
    val positionTimestamp: Long
)

@Composable
fun PositionScreen() {
    var activityName by remember { mutableStateOf("") }
    var latitude by remember { mutableStateOf("") }
    var longitude by remember { mutableStateOf("") }

    val positionList = remember { mutableStateListOf<Position>() }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Add New Position", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = activityName,
            onValueChange = { activityName = it },
            label = { Text("Activity Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = latitude,
            onValueChange = { latitude = it },
            label = { Text("Latitude") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = longitude,
            onValueChange = { longitude = it },
            label = { Text("Longitude") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            val newPosition = Position(
                positionID = System.currentTimeMillis().toString(),
                positionActivityID = System.currentTimeMillis().toString(), // Placeholder ID
                positionActivityName = activityName,
                positionLatitude = latitude.toDoubleOrNull() ?: 0.0,
                positionLongitude = longitude.toDoubleOrNull() ?: 0.0,
                positionTimestamp = System.currentTimeMillis()
            )

            positionList.add(newPosition)

            // Placeholder for backend call
            // createPosition(newPosition)

            // Clear form
            activityName = ""
            latitude = ""
            longitude = ""
        }) {
            Text("Add Position")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Saved Positions", style = MaterialTheme.typography.headlineSmall)

        LazyColumn {
            items(positionList) { pos ->
                Text("${pos.positionActivityName} @ (${pos.positionLatitude}, ${pos.positionLongitude})")
                HorizontalDivider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PositionScreenPreview() {
    PositionScreen()
}