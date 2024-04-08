package com.example.lockup.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.lockup.receivers.BroadcastForServiceStart;
import com.example.lockup.receivers.MainBroadcast;


public class ServiceBroadcastReceiver extends Service {
//    BroadcastForServiceStart broadcast=new BroadcastForServiceStart();

    @Override
    public void onCreate() {
        super.onCreate();
     /*   IntentFilter filter=new IntentFilter();
        filter.addAction("RestartService");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(broadcast,filter,RECEIVER_EXPORTED);
        }else {
            registerReceiver(broadcast,filter);

        }*/
        Log.d(TAG, "onCreate: Service created");
    }

    private static final String TAG="ServiceTag";
    MainBroadcast mainBroadcast=new MainBroadcast();
    // Register BroadcastForServiceStart receiver
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: Service start");


        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REPLACED);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(mainBroadcast,intentFilter,RECEIVER_EXPORTED);
        }
        else {
            registerReceiver(mainBroadcast,intentFilter);

        }


        return START_STICKY;
    }


    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    public void onDestroy() {
        super.onDestroy();
//        sendBroadcast(new Intent("RestartService"));

        Log.d(TAG, "onDestroy: Service destroyed");
       /* Intent broadcastIntent = new Intent("RESTART_SERVICE");
        sendBroadcast(broadcastIntent);*/


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
