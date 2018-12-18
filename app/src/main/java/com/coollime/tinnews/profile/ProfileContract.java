package com.coollime.tinnews.profile;

import com.coollime.tinnews.mvp.MvpContract;

public interface ProfileContract {

    interface View extends MvpContract.View<Presenter> {
        void setView();

        void onCacheCleared();
    }

    interface Presenter extends MvpContract.Presenter<View, Model> {
        void onCacheCleared();

        android.view.View.OnClickListener getCacheClearListener();
    }

    interface Model extends MvpContract.Model<Presenter> {
        void deleteAllNewsCache();
    }
}
