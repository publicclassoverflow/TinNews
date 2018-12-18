package com.coollime.tinnews.profile.country;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.coollime.tinnews.R;
import com.coollime.tinnews.mvp.MvpFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CountrySettingFragment extends MvpFragment<CountrySettingContract.Presenter> implements
        CountrySettingContract.View {

    private ListView countryGroup;

    public static CountrySettingFragment newInstance() {
        CountrySettingFragment fragment = new CountrySettingFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.country_setting_layout, container, false);
        countryGroup = view.findViewById(R.id.list_view);
        return view;
    }

    @Override
    public CountrySettingContract.Presenter getPresenter() {
        return new CountrySettingPresenter();
    }

    @Override
    public void loadCountries(List<Country> countryList) {
        ListViewAdapter adapter = new ListViewAdapter(countryList, getContext(), presenter.getCountryListener());
        countryGroup.setAdapter(adapter);
    }
}
