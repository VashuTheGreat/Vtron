//package com.example.vtron;
//
//import android.annotation.SuppressLint;
//import android.app.Notification;
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.webkit.JavascriptInterface;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.widget.Button;
//import android.widget.EditText;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.NotificationCompat;
//
//public class MainActivity extends AppCompatActivity {
//
//
//    WebView webView;
//    EditText urlInput;
//    Button loadButton;
//
//    @SuppressLint("SetJavaScriptEnabled")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
//                    != PackageManager.PERMISSION_GRANTED) {
//                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 101);
//            }
//        }
//
//
//
//
//
//        webView = new WebView(this);
//        urlInput = findViewById(R.id.urlInput);
//        loadButton = findViewById(R.id.loadButton);
//
//
//
//        setContentView(webView);
//
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.addJavascriptInterface(new VtronBridge(this), "vtron");
//
////        webView.loadUrl("file:///android_asset/index.html");
//        webView.loadUrl("https://vashuthegreat.github.io/Vtron_ui/");
//
//
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
//            Uri uri = data.getData();
//            if (uri != null) {
//                String filePath = uri.toString();
//                webView.evaluateJavascript("onFilePicked('" + filePath + "')", null);
//            }
//        }
//    }
//
//
//}








package com.example.vtron;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    EditText urlInput;
    Button loadBtn;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Use XML layout now

        // Notification permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }

        // Find views
        webView = findViewById(R.id.webView);
        urlInput = findViewById(R.id.urlInput);
        loadBtn = findViewById(R.id.loadBtn);

        // WebView settings
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new VtronBridge(this), "vtron");

        // Button click: load URL from EditText
        loadBtn.setOnClickListener(v -> {
            String url = urlInput.getText().toString().trim();
            if (!url.startsWith("http")) {
                url = "https://" + url;
            }
            webView.loadUrl(url);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            if (uri != null) {
                String filePath = uri.toString();
                webView.evaluateJavascript("onFilePicked('" + filePath + "')", null);
            }
        }
    }
}
