package com.example.lockup.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.example.lockup.database.LockUpDbAssign;
import com.example.lockup.database.LockUpHelper;

import java.util.List;

public class DbUpdateUtils {
    public static void synchronizeAppsInDatabase(Context context) {
        LockUpHelper lockUpHelper = LockUpHelper.getDb(context);
//        PackageManager packageManager = getApplicationContext().getPackageManager();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager = (PackageManager) context.getPackageManager();
        // Retrieve the list of installed applications
//        List<ApplicationInfo> installedApps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        List<ResolveInfo> appList = packageManager.queryIntentActivities(mainIntent,0);


        // Retrieve the list of apps stored in the database
        List<LockUpDbAssign> appsInDatabase = lockUpHelper.lockUpDao().getAllApps();

        // Add new installed apps to the database
        for (ResolveInfo appInfo : appList) {
            String packageName = appInfo.activityInfo.packageName;
            if (!isAppInDatabase(packageName, appsInDatabase)) {
                lockUpHelper.lockUpDao().addApp(new LockUpDbAssign(appInfo.loadLabel(packageManager).toString(), packageName, false));
            }
        }

        // Remove uninstalled apps from the database
        for (LockUpDbAssign app : appsInDatabase) {
            if (!isAppInstalled(app.getPackageName(), appList)) {
                lockUpHelper.lockUpDao().deleteAppByPackageName(app.getPackageName());
            }
        }
//        lockUpHelper.close();
    }

    private static boolean isAppInDatabase(String packageName, List<LockUpDbAssign> appsInDatabase) {
        for (LockUpDbAssign app : appsInDatabase) {
            if (packageName.equals(app.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isAppInstalled(String packageName, List<ResolveInfo> installedApps) {
        for (ResolveInfo appInfo : installedApps) {
            if (packageName.equals(appInfo.activityInfo.packageName)) {
                return true;
            }
        }
        return false;
    }
}
