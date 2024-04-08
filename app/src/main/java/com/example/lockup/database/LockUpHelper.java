package com.example.lockup.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = LockUpDbAssign.class,exportSchema = false,version = 2)
public abstract class LockUpHelper extends RoomDatabase {
 public static final String DB_NAME="applockerdb";
public static LockUpHelper instance;
public static synchronized LockUpHelper getDb(Context context){
 if (instance==null){
  instance= Room.databaseBuilder(context,LockUpHelper.class,DB_NAME)
          .fallbackToDestructiveMigration()
          .allowMainThreadQueries()
          .build();
 }
 return instance;
}



 public abstract LockUpDao lockUpDao();
}
