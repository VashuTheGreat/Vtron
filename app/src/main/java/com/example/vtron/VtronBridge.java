package com.example.vtron;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.webkit.JavascriptInterface;

import androidx.core.app.NotificationCompat;

public class VtronBridge {

    Context context;
    Activity activity;

//    Activity activity;
//
//
//    public VtronBridge(Activity activity){
//        this.activity=activity;
//    }

//    public VtronBridge(Context context) {
//        this.context = context;
//    }


    public VtronBridge(Activity activity) {
        this.context = activity.getApplicationContext();
        this.activity = activity;
    }

    @JavascriptInterface

    public void pickFile(){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        activity.startActivityForResult(Intent.createChooser(intent, "Select a file"), 101);
    }

    @JavascriptInterface
    public void vibrate() {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            }
        }
    }
    @JavascriptInterface
    public void showNotificationFromWeb(String title, String message) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "vtron_channel",
                    "Vtron Notification",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }

        Notification notification = new NotificationCompat.Builder(context, "vtron_channel")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.notify(2, notification);
        }
    }

    @JavascriptInterface
    public void scheduleTask(String taskCommand, long delayInMillis) {
        Intent intent = new Intent(context, TaskReceiver.class);
        intent.putExtra("taskCommand", taskCommand);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                taskCommand.hashCode(), // to support multiple tasks
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            long triggerTime = System.currentTimeMillis() + delayInMillis;
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
        }
    }



}
