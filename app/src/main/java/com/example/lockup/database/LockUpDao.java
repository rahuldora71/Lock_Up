package com.example.lockup.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface LockUpDao {
    @Query("Select * from appLockTable")
    List<LockUpDbAssign> getAllApps();
    @Insert
    void addApp(LockUpDbAssign lockUpDbAssign);
    @Query("SELECT COUNT(*) FROM appLockTable WHERE packageName = :packageName")
    int countAppsByPackageName(String packageName);
    @Query("DELETE FROM appLockTable WHERE packageName = :packageName")
    void deleteApp(String packageName);
    @Query("SELECT packageName FROM appLockTable WHERE aBoolean = 1")
    List<String> getLockedAppPackageNames();
/*    @Delete
    void deleteApp(LockUpDbAssign lockUpDbAssign);*/
    @Update
    void updateApp(LockUpDbAssign lockUpDbAssign);
    @Query("UPDATE appLockTable SET aBoolean = :newValue WHERE packageName = :packageName")
    void updateBooleanValueByPackageName(String packageName, boolean newValue);
    // Delete an app by its package name
    @Query("DELETE FROM appLockTable WHERE packageName = :packageName")
    void deleteAppByPackageName(String packageName);

}
