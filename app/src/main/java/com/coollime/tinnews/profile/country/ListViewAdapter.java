package com.coollime.tinnews.profile.country;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.coollime.tinnews.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewAdapter extends BaseAdapter {
    private List<Country> countries;
    private Context context;
    private CountryListener countryListener;

    ListViewAdapter(List<Country> countries, Context context, CountryListener countryListener) {
        this.countries = countries;
        this.context = context;
        this.countryListener = countryListener;
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.country_item_list, parent, false);
        }

        // Get and set up the country list and check marks
        Country country = countries.get(position);
        TextView textView = convertView.findViewById(R.id.row_text);
        textView.setText(getCountryName(country.getString()));
        ImageView flag = convertView.findViewById(R.id.flag);
        flag.setImageResource(country.getDrawableRes());

        ImageView checkMark = convertView.findViewById(R.id.checkmark);
        checkMark.setImageResource(country.isSelected() ? R.drawable.ic_check_circle_black_24dp :
                R.drawable.ic_check_circle_grey_24dp);

        convertView.setOnClickListener(v -> {
            unsetAdapter();
            country.setSelected(true);
            countryListener.onCountryClicked(country);
            notifyDataSetChanged();
        });
        return convertView;
    }

    private String getCountryName(String string) {
        Map<String, String> countryNames = new HashMap<>();
        countryNames.put("au", "Australia");
        countryNames.put("cn", "China");
        countryNames.put("de", "Germany");
        countryNames.put("fr", "France");
        countryNames.put("gb", "United Kingdom");
        countryNames.put("hk", "Hong Kong");
        countryNames.put("in", "India");
        countryNames.put("jp", "Japan");
        countryNames.put("kr", "South Korea");
        countryNames.put("mx", "Mexico");
        countryNames.put("tw", "Taiwan");
        countryNames.put("us", "United States");
        return countryNames.get(string);
    }

    private void unsetAdapter() {
        for (Country country : countries) {
            country.setSelected(false);
        }
    }

    public interface CountryListener {
        void onCountryClicked(Country country);
    }
}
