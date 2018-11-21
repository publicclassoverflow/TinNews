package com.coollime.tinnews.tin;

import com.coollime.tinnews.mvp.MvpContract;
import com.coollime.tinnews.retrofit.response.News;

import java.util.List;

public interface TinContract {
    interface View extends MvpContract.View<Presenter> {
        void showNewsCard(List<News> newsList);
    }

    interface Presenter extends MvpContract.Presenter<View, Model> {
        void showNewsCard(List<News> newsList);
        void saveFavoriteNews(News news);
    }

    interface Model extends MvpContract.Model<Presenter> {
        void fetchData(String country);
        void saveFavoriteNews(News news);
    }
}
