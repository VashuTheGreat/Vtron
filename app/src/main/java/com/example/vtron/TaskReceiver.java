package com.example.vtron;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;

import java.lang.reflect.Method;

public class TaskReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String taskCommand = intent.getStringExtra("taskCommand");

        if (taskCommand == null) return;


        try{
            if(taskCommand.startsWith("notify:")){
                String[] parts=taskCommand.substring(7).split("\\|");
                VtronBridge.showNotificationFromWeb(parts[0],parts[1]);
            } else if (taskCommand.equals("vibrate")) {

                VtronBridge.vibrate();
            }


        }
        catch (Exception e){
            e.printStackTrace();

        }

//        if (taskCommand.equals("vibrate")) {
//            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
//            if (vibrator != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
//            }
//        } else if (taskCommand.startsWith("notify:")) {
//            String[] parts = taskCommand.substring(7).split("\\|");
//            String title = parts.length > 0 ? parts[0] : "Vtron";
//            String message = parts.length > 1 ? parts[1] : "Background Message";
//
//            NotificationChannel channel = null;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                channel = new NotificationChannel(
//                        "vtron_channel",
//                        "Vtron Background",
//                        NotificationManager.IMPORTANCE_DEFAULT
//                );
//            }
//            NotificationManager manager = context.getSystemService(NotificationManager.class);
//            if (manager != null) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    manager.createNotificationChannel(channel);
//                }
//                Notification notification = new NotificationCompat.Builder(context, "vtron_channel")
//                        .setSmallIcon(R.drawable.ic_launcher_background)
//                        .setContentTitle(title)
//                        .setContentText(message)
//                        .build();
//                manager.notify((int) System.currentTimeMillis(), notification);
//            }
//        }
//
//        // aur bhi custom tasks yahan handle kar sakte ho
//    }


        }

}

