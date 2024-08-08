package com.hp.learnkotlin.ui.notifications

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.hp.learnkotlin.R

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("notification", "started ${intent.toString()}")
        val replyText = intent?.let { getMessageText(it) }
        Log.e("notification", "reply = $replyText")
        if (context != null) {
            val notificationData = NotificationData(
                channelId = AppNotification.DEFAULT_CHANNEL,
                contentTitle = "Notification Title",
                contentText = "Reply Received",
                notificationId = 12
            )
            AppNotification.sendNotification(context, notificationData)
        }
    }

    private fun getMessageText(intent: Intent): CharSequence? {
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        return remoteInput?.getCharSequence("key_text_reply")
    }
}