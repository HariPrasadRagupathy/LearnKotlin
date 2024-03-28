package com.hp.learnkotlin.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class NotificationData(val data: String)

@Composable
fun NotificationsContent() {
    LazyColumn(contentPadding = PaddingValues(vertical = 5.dp)) {
        items(15) { index ->
            NotificationCard(NotificationData("Data $index"))
        }
    }
}

@Composable
fun NotificationCard(data: NotificationData) {
    Card(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Text(text = data.data)
    }
}