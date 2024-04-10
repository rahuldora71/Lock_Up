package com.example.lockup.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.example.lockup.database.LockUpHelper;
import com.example.lockup.models.AppModel;

import java.util.ArrayList;

public class DataFromDbUtils {
    public static ArrayList<AppModel> fatchFromDb(Context context,ArrayList<AppModel>arrayList){
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
//        ArrayList<AppModel>arrayList=new ArrayList<>();
        LockUpHelper lockUpHelper=LockUpHelper.getDb(context);

        for (int i=0;i<lockUpHelper.lockUpDao().getAllApps().size();i++){
            String app_Name=lockUpHelper.lockUpDao().getAllApps().get(i).getAppName();
            String appPackage=lockUpHelper.lockUpDao().getAllApps().get(i).getPackageName();
            Drawable appIcon= getIconDrawableFromPackageName(appPackage,context.getPackageManager());
            boolean appbool;
            if (lockUpHelper.lockUpDao().getAllApps().get(i).isaBoolean()){
                appbool=lockUpHelper.lockUpDao().getAllApps().get(i).isaBoolean();
            }else {
                appbool=false;
            }

            arrayList.add(new AppModel(appIcon,app_Name,appPackage,appbool));

        }
//        lockUpHelper.close();
        return arrayList;
    }
    private static Drawable getIconDrawableFromPackageName(String packageName,PackageManager packageManager) {
        try {
//            PackageManager packageManager = getPackageManager();
            ApplicationInfo appInfo = packageManager.getApplicationInfo(packageName, 0);
            return appInfo.loadIcon(packageManager);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            // Handle exception if package name is not found
            return null;
        }
    }
}
