package com.coollime.tinnews.tin;

import com.coollime.tinnews.profile.country.CountryChangeEvent;
import com.coollime.tinnews.retrofit.response.News;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class TinPresenter implements TinContract.Presenter {
//    private final String DEFAULT_COUNTRY = "us";

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
//        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        // The event must be unregistered. Otherwise there will be memory leak
//        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onViewAttached(TinContract.View view) {
        EventBus.getDefault().register(this);
        // Hold the view reference
        this.view = view;
        // Start fetching data here
//        this.model.fetchData(DEFAULT_COUNTRY);
        this.model.fetchData(false);
    }

    @Override
    public void onViewDetached() {
        // Clear the view reference
        this.view = null;
        // Unregister the event. Otherwise there will be memory leak
        EventBus.getDefault().unregister(this);
    }

    /*
    @Override
    public void showNewsCard(List<News> newsList) {
        if (this.view != null) {
            view.showNewsCard(newsList);
        }
    }
    */

    @Override
    public void onNewsLoaded(List<News> newsList, boolean isClear) {
        if (view != null) {
            view.onNewsLoaded(newsList, isClear);
        }
    }

    @Override
    public void saveFavoriteNews(News news) {
        model.saveFavoriteNews(news);
    }

    /*
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CountryChangeEvent countryChangeEvent) {
        if (this.view != null) {
            this.model.fetchData(countryChangeEvent.country);
        }
    }
    */

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(CountryChangeEvent event) {
        model.fetchData(true);
    }

    @Override
    public void message(String string) {
        if (view != null) {
            view.message(string);
        }
    }

    @Override
    public void fetchData(boolean isClear) {
        if (this.view != null) {
            model.fetchData(false);
        }
    }
}
