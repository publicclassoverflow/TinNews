package com.coollime.tinnews.tin;

import com.coollime.tinnews.mvp.MvpContract;
import com.coollime.tinnews.retrofit.response.News;

import java.util.List;

public interface TinContract {
    interface View extends MvpContract.View<Presenter> {
//        void showNewsCard(List<News> newsList);

        void onNewsLoaded(List<News> newsList, boolean isClear);

        void message(String string);
    }

    interface Presenter extends MvpContract.Presenter<View, Model> {
//        void showNewsCard(List<News> newsList);
        void saveFavoriteNews(News news);

        void onNewsLoaded(List<News> newsList, boolean isClear);

        void message(String string);

        void fetchData(boolean isClear);
    }

    interface Model extends MvpContract.Model<Presenter> {
        void fetchData(boolean isClear);

        void saveFavoriteNews(News news);
    }
}
