package com.example.lockup.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.example.lockup.services.ServiceBroadcastReceiver;

public class BroadcastForServiceStart extends BroadcastReceiver {
    private static final String TAG="mukesh";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: BroadcastForServiceStart ");
        if (intent.getAction().equals("RestartService")) {
            // Start the service again
            Intent serviceIntent = new Intent(context, ServiceBroadcastReceiver.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(serviceIntent);
            } else {
                context.startService(serviceIntent);
            }
        }
/*
        if (intent.getAction().equals("RestartService")) {

            Intent serviceIntent = new Intent(context, ServiceBroadcastReceiver.class);
//            context.startService(serviceIntent);
//            context.startService(serviceIntent);
//            serviceIntent.setAction("ACTION_START_FOREGROUND");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(serviceIntent);
            } else {
                context.startService(serviceIntent);
            }
        }
*/
    }
}
