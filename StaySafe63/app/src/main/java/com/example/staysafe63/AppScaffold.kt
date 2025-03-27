package com.example.staysafe63

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
* @author K2128078
*
* */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    screenTitle: String,
    navController: NavController,
    showDrawer: Boolean = true,
    content: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Navigation", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(16.dp))

                DrawerItem("Activity", "activity_screen", navController, drawerState, scope)
                DrawerItem("Contacts", "contact_screen", navController, drawerState, scope)
                DrawerItem("Statuses", "status_screen", navController, drawerState, scope)
                DrawerItem("Profile", "user_screen", navController, drawerState, scope)
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(screenTitle) },
                    navigationIcon = {
                        if (showDrawer) {
                            IconButton(onClick = {
                                scope.launch { drawerState.open() }
                            }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu")
                            }
                        }
                    }
                )
            }
        ) { innerPadding ->
            Surface(modifier = Modifier.padding(innerPadding)) {
                content()
            }
        }
    }
}

@Composable
fun DrawerItem(
    label: String,
    route: String,
    navController: NavController,
    drawerState: DrawerState,
    scope: kotlinx.coroutines.CoroutineScope
) {
    NavigationDrawerItem(
        label = { Text(label) },
        selected = false,
        onClick = {
            navController.navigate(route)
            scope.launch { drawerState.close() }
        },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
    )
}
