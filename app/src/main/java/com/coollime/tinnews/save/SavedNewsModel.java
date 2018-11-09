package com.coollime.tinnews.save;

import android.annotation.SuppressLint;

import com.coollime.tinnews.TinApplication;
import com.coollime.tinnews.database.AppDatabase;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SavedNewsModel implements SavedNewsContract.Model {
    // Get the Presenter's reference
    private SavedNewsContract.Presenter presenter;
    // Get the database reference
    private final AppDatabase database;

    public SavedNewsModel() {
        database = TinApplication.getDatabase();
    }

    @Override
    public void setPresenter(SavedNewsContract.Presenter presenter) {
        // Hold the presenter reference
        this.presenter = presenter;
    }

    @SuppressLint("CheckResult")
    @Override
    public void fetchData() {
        database.newsDao().getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(presenter::loadSavedNews, error -> {
                    System.out.println("error");
                }, () -> {
                    System.out.println("complete");
                });
    }
}
