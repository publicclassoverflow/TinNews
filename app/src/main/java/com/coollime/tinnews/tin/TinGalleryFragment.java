package com.coollime.tinnews.tin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coollime.tinnews.R;
import com.coollime.tinnews.mvp.MvpFragment;
import com.coollime.tinnews.retrofit.response.News;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TinGalleryFragment extends MvpFragment<TinContract.Presenter> implements TinNewsCard.OnSwipeListener, TinContract.View {
    private SwipePlaceHolderView mSwipeView;
    private List<News> cacheNews = new ArrayList<>();
    private static final int MIN_NEWS = 4;

    public static TinGalleryFragment newInstance() {
        return new TinGalleryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tin_gallery, container, false);

        mSwipeView = view.findViewById(R.id.swipeView);

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tin_news_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tin_news_swipe_out_msg_view));

        view.findViewById(R.id.rejectBtn).setOnClickListener(v -> mSwipeView.doSwipe(false));

        view.findViewById(R.id.acceptBtn).setOnClickListener(v -> mSwipeView.doSwipe(true));

        return view;
    }

    @Override
    public void onLike(News news) {
        cacheNews.remove(news); // Prevent liked news from being shown again
        presenter.saveFavoriteNews(news);
        // Fetch more news if there are too few of them
        if (cacheNews.size() < MIN_NEWS) {
            presenter.fetchData(false);
        }
    }

    @Override
    public TinContract.Presenter getPresenter() {
        return new TinPresenter();
    }

    @Override
    public void onNewsLoaded(List<News> newsList, boolean isClear) {
        if (isClear) {
            cacheNews.clear();
            mSwipeView.removeAllViews();
        }
        cacheNews.addAll(newsList);
        for (News news : newsList) {
            mSwipeView.addView(new TinNewsCard(news, mSwipeView, this));
        }
    }

    @Override
    public void message(String string) {
        tinFragmentManager.showSnackBar(string);
    }
}
