package com.example.staysafe63.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.staysafe63.viewmodel.StepCountViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun StepTrackingScreen(viewModel: StepCountViewModel = viewModel()) {
    val stepCount by viewModel.stepCount.observeAsState(0)

    LaunchedEffect(Unit) {
        viewModel.startTracking()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Today's Steps", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        Text("$stepCount steps")
    }
}