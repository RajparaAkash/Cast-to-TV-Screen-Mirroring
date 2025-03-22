package com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.casttotv.screenmirroring.chromecast.smart.tv.Activity.ActivityDashboard;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;

public class AppNotifications {

    public static final String NOTIFICATIONS_CHANNEL_ID = "live_screen_channel";

    // Create the notification channel for API 26 and above
    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                NotificationChannel notificationChannel = new NotificationChannel(
                        NOTIFICATIONS_CHANNEL_ID,
                        "Screen Mirroring",
                        NotificationManager.IMPORTANCE_LOW
                );
                notificationChannel.setDescription("Cast to TV Notifications");
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                if (notificationManager != null) {
                    notificationManager.createNotificationChannel(notificationChannel);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Create a new service notification
    public static Notification newServiceNotification(Context context) {
        try {
            Context applicationContext = context.getApplicationContext();
            Bitmap largeIcon = BitmapFactory.decodeResource(applicationContext.getResources(), R.mipmap.ic_launcher);
            Intent intent = new Intent(applicationContext, ActivityDashboard.class);
            intent.setAction(LiveScreenIntent.NOTIFICATION_SHOW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            // Use FLAG_IMMUTABLE to ensure the PendingIntent cannot be modified
            PendingIntent activityIntent = PendingIntent.getActivity(
                    applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT
            );

            NotificationCompat.Builder builder = new NotificationCompat.Builder(applicationContext, NOTIFICATIONS_CHANNEL_ID)
                    .setContentTitle(applicationContext.getString(R.string.app_name))
                    .setContentText("Cast to tv is running")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(largeIcon)
                    .setOngoing(true)
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setCategory(NotificationCompat.CATEGORY_SERVICE)
                    .setContentIntent(activityIntent);

            return builder.build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
