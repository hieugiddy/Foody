package com.app.foody.Controller;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.app.foody.R;

public class NotificationHelper extends ContextWrapper {
    private static final String CHANNEL_NAME="Channel1";
    private static final String CHANNEL_DESCRIPTION="Mô tả Channel";
    private static final String CHANNEL_ID="Channel1";
    private static NotificationManager notificationManager;
    private Context base;
    public NotificationHelper(Context base) {
        super(base);
        this.base=base;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            taoChannel();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void taoChannel() {
        NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.setDescription(CHANNEL_DESCRIPTION);
        notificationChannel.enableVibration(true);
        notificationChannel.enableLights(true);
        notificationChannel.canShowBadge();
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        notificationChannel.setLightColor(getResources().getColor(R.color.white));
        getNotificationManager().createNotificationChannel(notificationChannel);
    }

    public NotificationManager getNotificationManager(){
        if(notificationManager!=null)
            notificationManager=(NotificationManager) base.getSystemService(Context.NOTIFICATION_SERVICE);
        return notificationManager;
    }

    public void notify(String message,String title){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(base,CHANNEL_ID);
        Uri rington= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setContentTitle(title);
        builder.setContentText(message);
        builder.setSound(rington);
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
        getNotificationManager().notify(9001,builder.build());
    }
}
