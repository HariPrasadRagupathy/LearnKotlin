package com.hp.learnkotlin.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hp.learnkotlin.ui.broadcasereceiver.BroadCastUIReceiver
import com.hp.learnkotlin.ui.broadcasereceiver.BroadCastUISender
import com.hp.learnkotlin.ui.components.common.CommonSettings
import com.hp.learnkotlin.ui.compose.addPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                colors = CommonSettings.getTopAppBarColors(),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, "")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding.addPadding(5.dp))) {
            Text("Settings Screen")
            BroadCastUISender()
            BroadCastUIReceiver()
        }
    }
}