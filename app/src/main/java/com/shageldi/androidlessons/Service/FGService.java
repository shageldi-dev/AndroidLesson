package com.shageldi.androidlessons.Service;

import static com.shageldi.androidlessons.App.CHANNEL_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.shageldi.androidlessons.MainActivity;
import com.shageldi.androidlessons.R;

public class FGService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent notificationIntent=new Intent(FGService.this, MainActivity.class);
        int flag=0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            flag=PendingIntent.FLAG_MUTABLE;
        }
        PendingIntent pendingIntent=PendingIntent.getActivity(FGService.this,
                0,notificationIntent,flag);
        Notification notification=new NotificationCompat.Builder(FGService.this,CHANNEL_ID)
                .setContentTitle(FGService.this.getResources().getString(R.string.running))
                .setContentText("GeekSpace")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build();

        notification.flags = Notification.FLAG_INSISTENT | Notification.FLAG_AUTO_CANCEL;
        startForeground(1,notification);
        return START_NOT_STICKY;
    }


}
