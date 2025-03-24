package com.example.staysafe63

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.staysafe63.ui.theme.StaySafe63Theme
import com.example.staysafe63.viewmodel.entitySpecificViewmodel.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StaySafe63Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val userViewModel: UserViewModel = viewModel()
                    val contactViewModel: ContactViewModel = viewModel()
                    val activityViewModel: ActivityViewModel = viewModel()
                    val locationViewModel: LocationViewModel = viewModel()
                    val positionViewModel: PositionViewModel = viewModel()
                    val statusViewModel: StatusViewModel = viewModel()
                }
            }
        }
    }
}
