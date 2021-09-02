package com.example.devan.medicine_reminder.View;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import com.example.devan.medicine_reminder.R;

import static android.provider.Settings.System.DEFAULT_NOTIFICATION_URI;

public class DisplayNotification extends ContextWrapper {

    public DisplayNotification(Context base) {
        super(base);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void createNotification(String messageDetails, String descriptionDetails) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String id = "my_channel_01";

            CharSequence name = "Remedaily";

            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel mChannel = null;

            mChannel = new NotificationChannel(id, name, importance);

            mChannel.setDescription("The Channel Remedaily uses notifications acccess for ring and vibrate.");
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.setSound(mChannel.getSound(), mChannel.getAudioAttributes());
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.createNotificationChannel(mChannel);

            String channelId = "my_channel_01";

            Notification notification = new Notification.Builder(getApplicationContext())
                    .setContentTitle("Medicine: "+messageDetails)
                    .setContentText(descriptionDetails+". Click to view details")
                    .setSmallIcon(R.drawable.hands).setSound(DEFAULT_NOTIFICATION_URI)
                    .setChannelId(channelId)
                    .setAutoCancel(true)
                    .setSound(mChannel.getSound(), mChannel.getAudioAttributes())
                    .build();

            notification.contentIntent=  PendingIntent.getActivity(this, 0,
                    new Intent(this, NotificationDisplayMedicine.class).putExtra("name",messageDetails).putExtra("description",descriptionDetails), PendingIntent.FLAG_CANCEL_CURRENT);

            mNotificationManager.notify(001, notification);

        }

        else {

            Notification notification =
                    new NotificationCompat.Builder(this.getApplicationContext())
                            .setSmallIcon(R.drawable.hands)
                            .setContentTitle("Medicine: "+messageDetails)
                            .setContentText(descriptionDetails+". Click to view details")
                            .setAutoCancel(true)
                            .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                            .setSound(DEFAULT_NOTIFICATION_URI)
                            .build();
            notification.contentIntent=  PendingIntent.getActivity(this, 0,
                    new Intent(this, NotificationDisplayMedicine.class).putExtra("name",messageDetails).putExtra("description",descriptionDetails), PendingIntent.FLAG_CANCEL_CURRENT);

            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            mNotificationManager.notify(001, notification);
        }
        EnableFlashLight enableFlashLight = new EnableFlashLight(this);
        enableFlashLight.enableFlash();
    }
}
