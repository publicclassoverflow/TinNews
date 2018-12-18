package com.coollime.tinnews.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.coollime.tinnews.R;
import com.coollime.tinnews.common.ViewModelAdapter;
import com.coollime.tinnews.mvp.MvpFragment;
import com.coollime.tinnews.profile.country.CountrySettingFragment;
import com.coollime.tinnews.save.detail.TitleViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TinProfileFragment extends MvpFragment<ProfileContract.Presenter> implements
        ProfileContract.View {

    private ViewModelAdapter viewModelAdapter;

    public static TinProfileFragment newInstance() {
        Bundle args = new Bundle();
        TinProfileFragment fragment = new TinProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tin_profile, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModelAdapter = new ViewModelAdapter();
        recyclerView.setAdapter(viewModelAdapter);
        return view;
    }

    @Override
    public ProfileContract.Presenter getPresenter() {
        return new ProfilePresenter();
    }

    @Override
    public void setView() {
        if (!viewModelAdapter.isEmpty()) {
            return;
        }
        // Settings title
        viewModelAdapter.addViewModel(
                new TitleViewModel(getString(R.string.setting), R.layout.setting_title_layout)
        );
        // Change country
        viewModelAdapter.addViewModel(new RowTextViewModel(getString(R.string.change_source), v -> {
            tinFragmentManager.doFragmentTransaction(CountrySettingFragment.newInstance());
        }));
        // Clear saved news
        viewModelAdapter.addViewModel(
                new RowTextViewModel(
                        getString(R.string.clear_cache), presenter.getCacheClearListener()
                )
        );
    }

    @Override
    public void onCacheCleared() {
        tinFragmentManager.showSnackBar(getString(R.string.cache_cleared));
        Toast.makeText(getContext(), "Saved news has been deleted", Toast.LENGTH_SHORT).show();
    }
}
