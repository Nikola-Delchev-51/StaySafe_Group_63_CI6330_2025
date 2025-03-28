package com.example.staysafe63.ui

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import android.location.Geocoder
import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import java.util.Locale


/**
 * @author K2336620
 *
 * */
fun getCoordinatesFromAddress(context: Context, address: String): Pair<Double, Double> {
    val geocoder = Geocoder(context, Locale.getDefault())
    if (address.isNullOrBlank()) {
        return Pair(0.0, 0.0)
    }
    val addresses = geocoder.getFromLocationName(address, 1)
    if (addresses != null) {
        return if (addresses.isNotEmpty()) {
            val location = addresses[0]
            Pair(location.latitude, location.longitude)
        } else {
            Pair(0.0, 0.0)
        }
    }
    return Pair(0.0, 0.0)
}

@Composable
fun AddressMapView(address: String) {

    val context = LocalContext.current
    var coordinates by remember { mutableStateOf<LatLng?>(null) }
    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(address) {
        val location = getCoordinatesFromAddress(context, address)
        if (location != Pair(0.0, 0.0)) {
            coordinates = LatLng(location.first, location.second)
            cameraPositionState.position = CameraPosition.fromLatLngZoom(coordinates!!, 15f)
        } else {
            coordinates = LatLng(51.402457053211, -0.30309028990825687)
            cameraPositionState.position = CameraPosition.fromLatLngZoom(coordinates!!, 15f)
        }
    }

    coordinates?.let {
        GoogleMap(
            modifier = Modifier.fillMaxSize().height(250.dp),
            cameraPositionState = cameraPositionState
        ) {
            Marker(state = MarkerState(position = it), title = address)
        }
    }
}
