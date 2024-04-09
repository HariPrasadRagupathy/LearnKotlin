package com.hp.learnkotlin.ui.broadcasereceiver

import android.content.Intent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun BroadCastUISender() {
    val context = LocalContext.current
    Button(onClick = {
        context.sendBroadcast(Intent("TEST_ACTION"))
    }) {
        Text("Trigger")
    }
}