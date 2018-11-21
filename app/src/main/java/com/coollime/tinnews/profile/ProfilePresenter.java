package com.coollime.tinnews.profile;

import android.view.View;

public class ProfilePresenter implements ProfileContract.Presenter {
    private ProfileContract.View view;
    private ProfileContract.Model model;

    ProfilePresenter() {
        this.model = new ProfileModel();
        this.model.setPresenter(this);
    }

    @Override
    public void onCacheCleared() {
        if (view != null) {
            view.onCacheCleared();
        }
    }

    @Override
    public View.OnClickListener getCacheClearListener() {
        return view -> model.deleteAllNewsCache();
    }

    @Override
    public View.OnClickListener getOnCountryChangeListener(String country) {
        return null;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onViewAttached(ProfileContract.View view) {
        // Keep the view
        this.view = view;
        this.view.setView();
    }

    @Override
    public void onViewDetached() {
        // Clear the view
        this.view = null;
    }
}
