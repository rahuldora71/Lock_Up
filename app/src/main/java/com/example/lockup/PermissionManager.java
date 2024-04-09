package com.example.lockup;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionManager {

    private static final int REQUEST_CODE_PERMISSIONS = 101;

    private static final String[] REQUIRED_PERMISSIONS = {
            Manifest.permission.FOREGROUND_SERVICE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    public static void requestPermissions(Activity activity) {
        // Check if all permissions are already granted
        if (hasAllPermissions(activity)) {
            // Permissions are granted, proceed with starting the service
            startService(activity);
            return;
        }

        // Request permissions from the user
        ActivityCompat.requestPermissions(activity, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
    }

    public static boolean hasAllPermissions(Activity activity) {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static void onRequestPermissionsResult(Activity activity, int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (hasAllPermissions(activity)) {
                // Permissions granted, start the service
                startService(activity);
            } else {
                // Permissions denied, handle the error or show a message to the user
            }
        }
    }

    private static void startService(Activity activity) {
        // Start the ServiceBroadcastReceiver service here
    }
}