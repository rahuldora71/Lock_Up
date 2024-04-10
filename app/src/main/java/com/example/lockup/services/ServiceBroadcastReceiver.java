package com.example.lockup.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;

import com.example.lockup.MainActivity;
import com.example.lockup.R;
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

    private static final String TAG = "ServiceTag";
    MainBroadcast mainBroadcast = new MainBroadcast();

    // Register BroadcastForServiceStart receiver
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Create a notification for the foreground service
      /*  Notification notification = new NotificationCompat.Builder(this, "lock_screen_channel")
                .setContentTitle("App Locker")
                .setContentText("Running in the background")
                .setSmallIcon(R.drawable.baseline_lock_24)
                .build();

        // Start the service as a foreground service*/
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification;
        final String CHANNEL_ID = "Push_noti";
        final int REQUEST_CODE = 90;

        Intent iNotify=new Intent(this, MainActivity.class);
        iNotify.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pi=PendingIntent.getActivity(this,REQUEST_CODE,iNotify,PendingIntent.FLAG_IMMUTABLE);

        CharSequence msg = "Running in the background";
        CharSequence title = "App Locker";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String name = "Custom Channel";
            String Description = "Channel for Push Notification";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel ;
            channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(Description);
            if (nm != null) {
                nm.createNotificationChannel(channel);
                notification = new Notification.Builder(this)
//                        .setSmallIcon(Icon.createWithBitmap(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.baseline_lock_24, null)).getBitmap()))
                        .setChannelId(CHANNEL_ID)
                        .setSmallIcon(R.drawable.baseline_lock_24)

                        .setContentIntent(pi)
//                    .setLargeIcon(((BitmapDrawable)ResourcesCompat.getDrawable(getResources(),R.drawable.database,null)).getBitmap())
                        .setSubText(msg)
                        .setContentTitle(title)
                        .setOngoing(true)
//                        .setAutoCancel(false)
                        .build();
                startForeground(125, notification);
            } else {
                notification = new Notification.Builder(this)
                        .setSmallIcon(R.drawable.baseline_lock_24)
                        .setContentIntent(pi)
                        .setSubText(msg)
//                    .setLargeIcon(((BitmapDrawable)ResourcesCompat.getDrawable(getResources(),R.drawable.database,null)).getBitmap())
                        .setContentTitle(title)
//                        .setAutoCancel(false)
                        .setOngoing(true)
                        .build();
                startForeground(125, notification);
            }
            /*if (nm!=null){
//                nm.notify(1,notification);
                startForegroundService(1,notification)
            }*/

        }
            Log.d(TAG, "onStartCommand: Service start");


            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Intent.ACTION_SCREEN_ON);
            intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
            intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
            intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
            intentFilter.addAction(Intent.ACTION_PACKAGE_REPLACED);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                registerReceiver(mainBroadcast, intentFilter, RECEIVER_EXPORTED);
            } else {
                registerReceiver(mainBroadcast, intentFilter);

            }


            return START_STICKY;
        }


        @Override
        public void onDestroy () {
            super.onDestroy();
//        sendBroadcast(new Intent("RestartService"));

            Log.d(TAG, "onDestroy: Service destroyed");
            Intent broadcastIntent = new Intent("RestartService");
            sendBroadcast(broadcastIntent);


        }

        @Override
        public IBinder onBind (Intent intent){
            return null;
        }


}