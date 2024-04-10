package com.example.lockup.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Objects;

public class MainBroadcast extends BroadcastReceiver {
    private static final String TAG="rahulreciver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d("ServiceBroadcastReceiver", "Received intent: " + action);

        Log.d(TAG, "onReceive: Started");
        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
            Log.d(TAG, "onReceive:  Screen on");
        }else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
            Log.d(TAG, "onReceive:  Screen Off");

        }else if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)){
            String packageName = Objects.requireNonNull(intent.getData()).getEncodedSchemeSpecificPart();
            Log.d(TAG, "Package Added: " + packageName);
            Log.d(TAG, "onReceive: Package Added ");

        }else if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)){
            String packageName = Objects.requireNonNull(intent.getData()).getEncodedSchemeSpecificPart();
            Log.d(TAG, "Package Removed: " + packageName);
            Log.d(TAG, "onReceive: Package Removed ");

        }else if (intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)){
            String packageName = Objects.requireNonNull(intent.getData()).getEncodedSchemeSpecificPart();
            Log.d(TAG, "Package Replaced: " + packageName);
            Log.d(TAG, "onReceive: Package Replaced ");

        }
    }
}
