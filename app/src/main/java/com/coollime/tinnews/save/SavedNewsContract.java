package com.coollime.tinnews.save;

import com.coollime.tinnews.mvp.MvpContract;
import com.coollime.tinnews.retrofit.response.News;

import java.util.List;

public interface SavedNewsContract {

    // MVP interface extended from MvpContract

    interface View extends MvpContract.View<Presenter> {
        void loadSavedNews(List<News> newsList);
    }

    interface Presenter extends MvpContract.Presenter<View, Model> {
        void loadSavedNews(List<News> newsList);

        SavedNewsPresenter.DeleteListener getOnDeleteListener();
    }

    interface Model extends MvpContract.Model<Presenter> {
        void fetchData();

        void deleteNews(News news);
    }
}
