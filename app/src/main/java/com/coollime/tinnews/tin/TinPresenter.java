package com.coollime.tinnews.tin;

import com.coollime.tinnews.retrofit.response.News;

import java.util.List;

public class TinPresenter implements TinContract.Presenter {
    // Hold the view reference
    private TinContract.View view;
    // Link the TinPresenter with TinModel
    private TinContract.Model model;

    public TinPresenter() {
        this.model = new TinModel();
        this.model.setPresenter(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onViewAttached(TinContract.View view) {
        // Hold the view reference
        this.view = view;
        // Start fetching data here
        this.model.fetchData();
    }

    @Override
    public void onViewDetached() {
        // Clear the view reference
        this.view = null;
    }

    @Override
    public void showNewsCard(List<News> newsList) {
        if (this.view != null) {
            view.showNewsCard(newsList);
        }
    }

    @Override
    public void saveFavoriteNews(News news) {
        model.saveFavoriteNews(news);
    }
}
