package com.example.surefitness;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHelper {
    private static final String CHANNEL_ID = "reminder_channel";

    public static void createNotification(Context context) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Create notification channel (needed for Android 8.0+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Daily Reminder",
                    NotificationManager.IMPORTANCE_HIGH
            );
            manager.createNotificationChannel(channel);
        }

        // Intent to open the app when notification is clicked
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // Build notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.sf) // notification icon
                .setContentTitle("Reminder")
                .setContentText("Have you completed your task for the day?")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // Show notification
        manager.notify(1, builder.build());
    }
}
