package com.hp.learnkotlin.ui.notifications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import com.hp.learnkotlin.R

@Preview(showBackground = true)
@Composable
fun NotificationDemo() {

    val context = LocalContext.current
    val notificationData = NotificationData(
        channelId = AppNotification.DEFAULT_CHANNEL,
        contentTitle = "Notification Title",
        contentText = "This is a basic notification",
        notificationId = 12
    )


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { AppNotification.sendNotification(context, notificationData) }) {
            Text("Basic Notification")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { AppNotification.showReplyNotification(context, notificationData.copy(notificationId = 13)) }) {
            Text("Reply Notification")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { AppNotification.showBubbleNotification(context, notificationData.copy(notificationId = 14, contentText = "This is Bubble Notification")) }) {
            Text("Bubble Notification")
        }
    }

}