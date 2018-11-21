package com.coollime.tinnews.tin;

import com.coollime.tinnews.profile.CountryEvent;
import com.coollime.tinnews.retrofit.response.News;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class TinPresenter implements TinContract.Presenter {
    private final String DEFAULT_COUNTRY = "us";

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
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        // The event must be unregistered. Otherwise there will be memory leak
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onViewAttached(TinContract.View view) {
        // Hold the view reference
        this.view = view;
        // Start fetching data here
        this.model.fetchData(DEFAULT_COUNTRY);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CountryEvent countryEvent) {
        if (this.view != null) {
            this.model.fetchData(countryEvent.country);
        }
    }

}
