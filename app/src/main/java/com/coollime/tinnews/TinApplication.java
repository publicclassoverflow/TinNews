package com.coollime.tinnews;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.coollime.tinnews.database.AppDatabase;
import com.facebook.stetho.Stetho;

public class TinApplication extends Application {
    public static AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        // Init Room database
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tin_db").build();
    }

    public static AppDatabase getDatabase() {
        return database;
    }
}
