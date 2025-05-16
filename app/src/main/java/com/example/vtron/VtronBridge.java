package com.example.vtron;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Camera;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.webkit.JavascriptInterface;

import androidx.core.app.NotificationCompat;

public class VtronBridge {

    private static CameraManager cameraManager;
    private static String cameraId;

    private static boolean isFlashOn=false;

    static Context context;
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
    public void toggleFlashlight(){
        try{

            if(cameraManager==null){
                cameraManager=(CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
                cameraId=cameraManager.getCameraIdList()[0];
            }

            isFlashOn=!isFlashOn;
            cameraManager.setTorchMode(cameraId,isFlashOn);


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    @JavascriptInterface
    public int getBatteryLevel() {
        Intent batteryIntent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = 0;
        int scale = 0;
        if (batteryIntent != null) {
            level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

            if (level >= 0 && scale > 0) {
                return Math.round((level * 100f) / scale);
            }
        }

        Log.d("BatteryCheck", "Level: " + level + ", Scale: " + scale);
        Log.d("VTRON", "Battery Level: " + level);
        System.out.print(level + ", Scale: " + scale);


        return -1;
    }
    @JavascriptInterface

    public void pickFile(){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        activity.startActivityForResult(Intent.createChooser(intent, "Select a file"), 101);
    }

    @JavascriptInterface
    public static void vibrate() {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            }
        }
    }
    @JavascriptInterface
    public static void showNotificationFromWeb(String title, String message) {
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

    @SuppressLint("ScheduleExactAlarm")
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
