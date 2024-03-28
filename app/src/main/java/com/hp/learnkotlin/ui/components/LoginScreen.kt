package com.hp.learnkotlin.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.hp.learnkotlin.ui.nav.ScreenGroup
import com.hp.learnkotlin.ui.nav.Screens

@Composable
fun LoginScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            navController.popBackStack(ScreenGroup.AuthGroup.route, true)
            navController.navigate(ScreenGroup.DashBoardGroup.route)
        }) {
            Text("Login")
        }
    }
}