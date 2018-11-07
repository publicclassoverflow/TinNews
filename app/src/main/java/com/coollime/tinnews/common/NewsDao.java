package com.coollime.tinnews.common;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.coollime.tinnews.retrofit.response.News;

@Dao
public interface NewsDao {
    @Insert
    void insertNews(News news);
}
