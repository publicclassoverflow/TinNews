package com.coollime.tinnews.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.coollime.tinnews.common.NewsDao;
import com.coollime.tinnews.retrofit.response.News;

@Database(entities = {News.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDao newsDao();
}
