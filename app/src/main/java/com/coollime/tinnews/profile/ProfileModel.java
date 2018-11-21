package com.coollime.tinnews.profile;

import com.coollime.tinnews.TinApplication;
import com.coollime.tinnews.database.AppDatabase;

public class ProfileModel implements ProfileContract.Model {
    private ProfileContract.Presenter presenter;
    private AppDatabase db = TinApplication.getDatabase();

    @Override
    public void deleteAllNewsCache() {

    }

    @Override
    public void setDefaultCountry(String country) {

    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
