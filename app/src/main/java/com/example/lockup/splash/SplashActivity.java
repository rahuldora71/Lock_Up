/*
package com.example.lockup.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.graphics.drawable.BitmapDrawable;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lockup.MainActivity;
import com.example.lockup.R;
import com.example.lockup.models.AppModel;
import android.graphics.Canvas;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {
    private ArrayList<AppModel> appArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
// Get the current time before iterating through the app list
        long startTime = System.currentTimeMillis();
//         Code to retrive the list of application installed in my phone

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager = getApplicationContext().getPackageManager();

        List<ResolveInfo> appList = packageManager.queryIntentActivities(mainIntent, 0);

        for (ResolveInfo resolveInfo : appList) {
            String appName = resolveInfo.loadLabel(packageManager).toString();
            Drawable appIcon = resolveInfo.loadIcon(packageManager);
            String packageName = resolveInfo.activityInfo.packageName;
            String activityName = resolveInfo.activityInfo.name;

            // Convert Drawable to Bitmap
            Bitmap bitmap = null;
            if (appIcon instanceof BitmapDrawable) {
                bitmap = ((BitmapDrawable) appIcon).getBitmap();
            } else {
                // If the Drawable is not a BitmapDrawable, you may need to create a new Bitmap
                bitmap = Bitmap.createBitmap(appIcon.getIntrinsicWidth(), appIcon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                appIcon.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                appIcon.draw(canvas);
            }

            // Save Bitmap to a file
            String imagePath = saveBitmapToFile(bitmap, appName);
            appArrayList.add(new AppModel(appName, imagePath)); // Pass imagePath instead of appIcon

            // Log.d("App_Pack", packageName + " : " + activityName);
        }



      */
/*  Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager = (PackageManager) getApplicationContext().getPackageManager();

        List<ResolveInfo> appList = packageManager.queryIntentActivities(mainIntent,0);

        for(ResolveInfo resolveInfo : appList) {
            String appName = resolveInfo.loadLabel(packageManager).toString();
            Drawable appIcon=resolveInfo.loadIcon(packageManager);
            String packageName = resolveInfo.activityInfo.packageName;
            String activityName = resolveInfo.activityInfo.name;

            appArrayList.add(new AppModel(appName,appIcon));
//            Log.d("App_Pack", packageName+"  :  "+activityName);
        }*//*

//       End of Code to retrive the list of application installed in my phone

// Get the current time after iterating through the app list
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        Log.d("AppListTime", "onCreate: "+elapsedTime);
        long mainTime=0;
        if (elapsedTime <= 6000) {
            mainTime=6000;
        } else {
            mainTime=6000+elapsedTime;

        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences=getSharedPreferences("logIn",MODE_PRIVATE);
                Boolean check=preferences.getBoolean("flag",false);

                Intent intent;
//                if (check){

                    intent=new Intent(SplashActivity.this, MainActivity.class);

//                    intent.putParcelableArrayListExtra("appList",appArrayList);
               */
/* }else {
                    intent = new Intent(SplashActivity.this, LogInActivity.class);
                }*//*

                startActivity(intent);
                finish();
            }
        },mainTime);



    }
*/
/*    private String saveBitmapToFile(Bitmap bitmap, String fileName) {
        // Get the directory to save the image
        File directory = new File(Environment.getExternalStorageDirectory() + File.separator + "app_icons");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Create a file to save the image
        File file = new File(directory, fileName + ".png");
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            return file.getAbsolutePath();
        } catch (IOException FileError) {
            FileError.printStackTrace();
            return null;
        }
    }*//*

private String saveBitmapToFile(Bitmap bitmap, String fileName) {
    // Get the directory to save the image
    File directory = new File(Environment.getExternalStorageDirectory() + File.separator + "app_icons");
    if (!directory.exists()) {
        directory.mkdirs();
    }

    // Create a file to save the image
    File file = new File(directory, fileName + ".png");
    try {
        FileOutputStream outputStream = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        outputStream.flush();
        outputStream.close();
        return file.getAbsolutePath(); // Return file path instead of null
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
}

}*/
