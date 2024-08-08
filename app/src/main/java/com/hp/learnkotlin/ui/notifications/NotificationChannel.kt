package com.hp.learnkotlin.ui.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent


import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import androidx.core.graphics.drawable.IconCompat
import com.hp.learnkotlin.MainActivity
import com.hp.learnkotlin.R

data class NotificationData(
    val notificationId: Int = 0,
    val channelId: AppNotification = AppNotification.DEFAULT_CHANNEL,
    val smallIcon: Int = R.drawable.ic_launcher_foreground,
    val contentTitle: String,
    val contentText: String,
    val priority: Int = NotificationCompat.PRIORITY_DEFAULT,
    val autoCancel: Boolean = true
)

enum class AppNotification(
    private val id: String,
    private val channelName: String,
    private val importance: Int,
    private val descriptionText: String
) {
    DEFAULT_CHANNEL(
        "default_channel_id",
        "Default Channel",
        NotificationManager.IMPORTANCE_DEFAULT,
        descriptionText = "descriptionText"
    );

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(): NotificationChannel {
        return NotificationChannel(
            id, channelName, importance
        ).apply {
            description = descriptionText
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                setAllowBubbles(true)
            }
        }
    }

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        private val channelList = mutableListOf<NotificationChannel>().apply {
            AppNotification.values().forEach {
                add(it.createChannel())
            }
        }

        fun createNotificationChannels(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationManager: NotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannels(AppNotification.channelList)
            }
        }

        fun showBubbleNotification(context: Context, notificationData: NotificationData) {
            val target = Intent(context, MainActivity::class.java)
            val bubbleIntent = PendingIntent.getActivity(
                context,
                0,
                target,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            )

            val bubbleData = NotificationCompat.BubbleMetadata.Builder(
                bubbleIntent,
                IconCompat.createWithResource(context, notificationData.smallIcon)
            )
                .setDesiredHeight(600)
                .setIcon(IconCompat.createWithResource(context, notificationData.smallIcon))
                .setIntent(bubbleIntent)
                .build()

            val notification =
                NotificationCompat.Builder(context, AppNotification.DEFAULT_CHANNEL.id)
                    .setSmallIcon(notificationData.smallIcon)
                    .setContentTitle(notificationData.contentTitle)
                    .setContentText(notificationData.contentText)
                    .setBubbleMetadata(bubbleData)
                    .setCategory(Notification.CATEGORY_MESSAGE)
                    .build()

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(notificationData.notificationId, notification)

        }

        fun showReplyNotification(context: Context, notificationData: NotificationData) {
            val replyLabel = "Reply"
            val KEY_TEXT_REPLY = "key_text_reply"
            val remoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
                setLabel(replyLabel)
                build()
            }

            val replyIntent = Intent(context, NotificationReceiver::class.java)
            val replyPendingIntent: PendingIntent =
                PendingIntent.getBroadcast(
                    context,
                    0,
                    replyIntent,
                    PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                )
            val replyAction = NotificationCompat.Action.Builder(
                R.drawable.ic_launcher_foreground,
                "Reply",
                replyPendingIntent
            ).addRemoteInput(remoteInput).build()

            val notification = NotificationCompat.Builder(context, notificationData.channelId.id)
                .setSmallIcon(notificationData.smallIcon)
                .setContentTitle(notificationData.contentTitle)
                .setContentText(notificationData.contentText)
                .addAction(replyAction)
                .build()

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(notificationData.notificationId, notification)

        }

        fun sendNotification(context: Context, notificationData: NotificationData) {

            val intent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            // val pendingIntent: PendingIntent =
            // PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_MUTABLE)

            //  val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            val detailsIntent = Intent(context, MainActivity::class.java).apply {
                action = "com.example.ACTION_OPEN_DETAILS"
                putExtra("navigate_to", "details")
            }
            val detailsPendingIntent: PendingIntent = PendingIntent.getActivity(
                context,
                0,
                detailsIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val dismissIntent = Intent(context, NotificationReceiver::class.java).apply {
                action = "com.example.ACTION_DISMISS"
            }

            val dismissPendingIntent: PendingIntent = PendingIntent.getBroadcast(
                context,
                1,
                dismissIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val builder = NotificationCompat.Builder(context, notificationData.channelId.id)
                .setSmallIcon(notificationData.smallIcon)
                .setContentTitle(notificationData.contentTitle)
                .setContentText(notificationData.contentText)
                .setPriority(notificationData.priority)
                .setContentIntent(detailsPendingIntent)
                .setAutoCancel(notificationData.autoCancel)
                .addAction(
                    R.drawable.ic_launcher_foreground,
                    "View Notifications",
                    detailsPendingIntent
                )
                .addAction(R.drawable.ic_launcher_foreground, "Dismiss", dismissPendingIntent)

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(notificationData.notificationId, builder.build())
        }

    }
}


