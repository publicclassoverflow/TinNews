package com.coollime.tinnews.tin;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import com.coollime.tinnews.TinApplication;
import com.coollime.tinnews.database.AppDatabase;
import com.coollime.tinnews.retrofit.NewsRequestApi;
import com.coollime.tinnews.retrofit.RetrofitClient;
import com.coollime.tinnews.retrofit.response.News;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.coollime.tinnews.profile.country.CountrySettingModel.COUNTRY;

public class TinModel implements TinContract.Model {
    private TinContract.Presenter presenter;
    private final NewsRequestApi newsRequestApi;
    private final AppDatabase db;
    private final SharedPreferences sharedPreferences;

    public TinModel() {
        newsRequestApi = RetrofitClient.getInstance().create(NewsRequestApi.class);
        db = TinApplication.getDatabase();
        sharedPreferences = TinApplication.getSharedPreferences();
    }


    @Override
    public void setPresenter(TinContract.Presenter presenter) {
        // Assign the presenter
        this.presenter = presenter;
    }

    /*
    @SuppressLint("CheckResult")
    @Override
    public void fetchData(boolean country) {
        // Make the request to fetch data in the Model
        newsRequestApi.getNewsByCountry(country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(baseResponse -> baseResponse != null && baseResponse.articles != null)
                .subscribe(baseResponse -> presenter.showNewsCard(baseResponse.articles), error -> {
                });
    }
    */

    @SuppressLint("CheckResult")
    @Override
    public void fetchData(boolean isClear) {
        Observable<String> observable = Observable.just(sharedPreferences.getString(COUNTRY, "us"));
        observable.flatMap(s -> newsRequestApi.getNewsByCountry(s))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(baseResponse -> baseResponse != null && baseResponse.articles != null)
                .subscribe(baseResponse -> {
                    presenter.onNewsLoaded(baseResponse.articles, isClear);
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveFavoriteNews(News news) {
        Completable.fromAction(() -> db.newsDao().insertNews(news))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                }, error -> {
                    presenter.message("You have already saved this news");
                });
    }
}
