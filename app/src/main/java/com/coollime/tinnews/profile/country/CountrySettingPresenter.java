package com.coollime.tinnews.profile.country;

import com.coollime.tinnews.R;

import java.util.ArrayList;
import java.util.List;

public class CountrySettingPresenter implements CountrySettingContract.Presenter {

    private final CountrySettingContract.Model model;
    private CountrySettingContract.View view;

    public CountrySettingPresenter() {
        // Instantiate the model to set the presenter
        model = new CountrySettingModel();
        model.setPresenter(this);
    }

    @Override
    public ListViewAdapter.CountryListener getCountryListener() {
        return country -> {
            model.setDefaultCountry(country);
        };
    }

    @Override
    public void setDefaultCountry(String country) {
        if (view != null) {
            List<Country> countryList = new ArrayList<>();
            countryList.add(new Country(R.drawable.ic_country_us, view.getString(R.string.us),
                    "us", "us".equals(country)));
            countryList.add(new Country(R.drawable.ic_country_cn, view.getString(R.string.cn),
                    "cn", "cn".equals(country)));
            countryList.add(new Country(R.drawable.ic_country_gb, view.getString(R.string.gb),
                    "gb", "gb".equals(country)));
            countryList.add(new Country(R.drawable.ic_country_hk, view.getString(R.string.hk),
                    "hk", "hk".equals(country)));
            countryList.add(new Country(R.drawable.ic_country_tw, view.getString(R.string.tw),
                    "tw", "tw".equals(country)));
            countryList.add(new Country(R.drawable.ic_country_fr, view.getString(R.string.fr),
                    "fr", "fr".equals(country)));
            countryList.add(new Country(R.drawable.ic_country_au, view.getString(R.string.au),
                    "au", "au".equals(country)));
            countryList.add(new Country(R.drawable.ic_country_jp, view.getString(R.string.jp),
                    "jp", "jp".equals(country)));
            countryList.add(new Country(R.drawable.ic_country_kr, view.getString(R.string.kr),
                    "kr", "kr".equals(country)));
            countryList.add(new Country(R.drawable.ic_country_mx, view.getString(R.string.mx),
                    "mx", "mx".equals(country)));
            countryList.add(new Country(R.drawable.ic_country_de, view.getString(R.string.de),
                    "de", "de".equals(country)));
            countryList.add(new Country(R.drawable.ic_country_in, view.getString(R.string.in),
                    "in", "in".equals(country)));
            // Load the country list
            view.loadCountries(countryList);
        }
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onViewAttached(CountrySettingContract.View view) {
        this.view = view;
        model.getSavedCountry();
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }
}
