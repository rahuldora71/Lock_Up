/*
package com.example.lockup.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.lockup.models.AppModel;

import java.util.ArrayList;
import java.util.List;

public class AppListArray {

*/
/*    public static ArrayList<AppModel> allApps(Context context){
        ArrayList<AppModel> appModels=new ArrayList<>();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager = (PackageManager) context.getPackageManager();
        List<ResolveInfo> appList = packageManager.queryIntentActivities(mainIntent,0);
        for(ResolveInfo resolveInfo : appList) {
            String appName = resolveInfo.loadLabel(packageManager).toString();
            Drawable appIcon=resolveInfo.loadIcon(packageManager);
            String packageName = resolveInfo.activityInfo.packageName;
            String activityName = resolveInfo.activityInfo.name;
            appModels.add(new AppModel(appName,appIcon));
//            Log.d("App_Pack", packageName+"  :  "+activityName);
        }
        return appModels;
    }*//*

*/
/*    public static ArrayList<String> getAllActivityNames(Context context){
        ArrayList<String> appModels=new ArrayList<>();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager = (PackageManager) context.getPackageManager();

        List<ResolveInfo> appList = packageManager.queryIntentActivities(mainIntent,0);

        for(ResolveInfo resolveInfo : appList) {
            String appName = resolveInfo.loadLabel(packageManager).toString();
            Drawable appIcon=resolveInfo.loadIcon(packageManager);
            String packageName = resolveInfo.activityInfo.packageName;
            String activityName = resolveInfo.activityInfo.name;

            appModels.add(appName);

//            Log.d("App_Pack", packageName+"  :  "+activityName);


        }

        return appModels;
    }*//*

*/
/*    public static ArrayList<String> getAllActivityNames(Context context) {
        ArrayList<String> activityNames = new ArrayList<>();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager = context.getPackageManager();

        List<ResolveInfo> appList = packageManager.queryIntentActivities(mainIntent, 0);

        for (ResolveInfo resolveInfo : appList) {
            String activityName = resolveInfo.activityInfo.name;
            activityNames.add(activityName);
            Log.d("App_Pack", activityName);
        }

        return activityNames;
    }*//*

}
*/
