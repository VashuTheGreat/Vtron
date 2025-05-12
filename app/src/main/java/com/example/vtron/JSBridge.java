package com.example.vtron;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class JSBridge {
    Context context;

    JSBridge(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void sendCommand(String cmd){
        switch (cmd){
            case "getBatteryLevel":
                //battery logic
                break;

            case "startService":
                // start a background service
                break;
        }

    }
    @JavascriptInterface
    public void startBackgroundService(){
        Intent intent=new Intent(context,MyService.class);
        context.startService(intent);
    }
public void call(String function,String jsonParams){
        switch (function){
            case "vibrate":
                Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(500);
                break;

        }
}
}