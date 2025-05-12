package com.example.vtron;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;


public class MyService extends Service {
    @SuppressLint("ForegroundServiceType")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MainActivity", "Trying to start service");
        Notification notification=new NotificationCompat.Builder(this,"alarm_channel")
                .setContentTitle("Alarm App")
                .setContentText("Alarm is active")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .build();

        startForeground(1,notification);

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



}
