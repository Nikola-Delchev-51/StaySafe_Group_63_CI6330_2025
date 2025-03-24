package com.example.staysafe63

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

// Data class for Location
data class Location(
    val locationID: String,
    val locationName: String,
    val locationDescription: String,
    val locationAddress: String,
    val locationPostcode: String,
    val locationLatitude: Double,
    val locationLongitude: Double
)

@Composable
fun LocationScreen() {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var postcode by remember { mutableStateOf("") }
    var latitude by remember { mutableStateOf("") }
    var longitude by remember { mutableStateOf("") }

    val locationList = remember { mutableStateListOf<Location>() }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Add New Location", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Location Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = postcode,
            onValueChange = { postcode = it },
            label = { Text("Postcode") },
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
            val newLocation = Location(
                locationID = System.currentTimeMillis().toString(),
                locationName = name,
                locationDescription = description,
                locationAddress = address,
                locationPostcode = postcode,
                locationLatitude = latitude.toDoubleOrNull() ?: 0.0,
                locationLongitude = longitude.toDoubleOrNull() ?: 0.0
            )

            locationList.add(newLocation)

            // Placeholder for backend - will call later
            // createLocation(newLocation)

            // Clear form
            name = ""
            description = ""
            address = ""
            postcode = ""
            latitude = ""
            longitude = ""
        }) {
            Text("Add Location")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Saved Locations", style = MaterialTheme.typography.headlineSmall)

        LazyColumn {
            items(locationList) { location ->
                Text("${location.locationName} - ${location.locationPostcode}")
                HorizontalDivider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LocationScreenPreview() {
    LocationScreen()
}
