package com.coollime.tinnews.save;

import com.coollime.tinnews.retrofit.response.News;

import java.util.List;

public class SavedNewsPresenter implements SavedNewsContract.Presenter {
    // Get the references
    private final SavedNewsContract.Model model;
    private SavedNewsContract.View view;

    public SavedNewsPresenter() {
        model = new SavedNewsModel();
        model.setPresenter(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onViewAttached(SavedNewsContract.View view) {
        // Link the view and model
        this.view = view;
        // Model fetches data
        this.model.fetchData();
    }

    @Override
    public void onViewDetached() {
        // Clear the view
        this.view = null;
    }

    @Override
    public void loadSavedNews(List<News> newsList) {
        if (view != null) {
            // Ask the view to load the news
            view.loadSavedNews(newsList);
        }
    }
}
