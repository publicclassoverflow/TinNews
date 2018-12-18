package com.coollime.tinnews;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;

import com.coollime.tinnews.database.AppDatabase;
import com.facebook.stetho.Stetho;

public class TinApplication extends Application {
    public static AppDatabase database;
    private static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        // Init Room database
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tin_db").build();
        // SharedPreferences
        sharedPreferences = getApplicationContext().getSharedPreferences("TinNews", Context.MODE_PRIVATE);
    }

    public static AppDatabase getDatabase() {
        return database;
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
