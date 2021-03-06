package com.coollime.tinnews.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.coollime.tinnews.retrofit.response.News;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface NewsDao {
    @Insert
    void insertNews(News news);

    @Query("SELECT * FROM news")
    Flowable<List<News>> getAll();

    // Clear all cached news
    @Query("DELETE FROM news")
    void deleteAllNews();

    // Clear only selected news
    @Delete
    void delete(News news);
}
