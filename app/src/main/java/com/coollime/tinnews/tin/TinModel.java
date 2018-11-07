package com.coollime.tinnews.tin;

import com.coollime.tinnews.TinApplication;
import com.coollime.tinnews.database.AppDatabase;
import com.coollime.tinnews.retrofit.NewsRequestApi;
import com.coollime.tinnews.retrofit.RetrofitClient;
import com.coollime.tinnews.retrofit.response.News;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TinModel implements TinContract.Model {
    // Keep the reference of TinContract.Presenter
    private TinContract.Presenter presenter;

    // Move the Retrofit client here
    private final NewsRequestApi newsRequestApi;

    // Keep the reference of database
    private final AppDatabase db;

    public TinModel() {
        newsRequestApi = RetrofitClient.getInstance().create(NewsRequestApi.class);
        db = TinApplication.getDatabase();
    }


    @Override
    public void setPresenter(TinContract.Presenter presenter) {
        // Assign the presenter
        this.presenter = presenter;
    }

    @Override
    public void fetchData() {
        // Make the request to fetch data in the Model
        newsRequestApi.getNewsByCountry("us")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(baseResponse -> baseResponse != null && baseResponse.articles != null)
                .subscribe(baseResponse -> {
                    presenter.showNewsCard(baseResponse.articles);
                }, error -> {
                });
    }

    @Override
    public void saveFavoriteNews(News news) {
        Disposable disposable = Completable.fromAction(() -> db.newsDao().insertNews(news))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() -> {

                }, error -> {
                });
    }
}
