package com.example.lockup.database;

import android.graphics.drawable.Drawable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "appLockTable")
public class LockUpDbAssign {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "appName")
    String appName;
    @ColumnInfo(name = "packageName")
    String packageName;
    @ColumnInfo(name = "aBoolean")
    boolean aBoolean;

    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }



   /* @ColumnInfo(name = "appImage")
    Drawable appImage;*/

    public LockUpDbAssign(String appName, String packageName, boolean aBoolean) {
        this.appName = appName;
        this.packageName = packageName;
        this.aBoolean = aBoolean;
    }

    public LockUpDbAssign() {
    }

    public LockUpDbAssign(String packageName) {
        this.packageName = packageName;
    }

 /*   public LockUpDbAssign(String appName, Drawable appImage) {
        this.appName = appName;
//        this.appImage = appImage;
    }*/

    public LockUpDbAssign(String appName, String packageName) {
        this.appName = appName;
        this.packageName = packageName;
    }

   /* public LockUpDbAssign(String appName, String packageName, Drawable appImage) {
        this.appName = appName;
        this.packageName = packageName;
//        this.appImage = appImage;
    }*/

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public  String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

   /* public Drawable getAppImage() {
        return appImage;
    }

    public void setAppImage(Drawable appImage) {
        this.appImage = appImage;
    }*/
}
