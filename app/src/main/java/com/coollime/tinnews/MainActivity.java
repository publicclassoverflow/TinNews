package com.coollime.tinnews;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.coollime.tinnews.common.ContainerFragment;
import com.coollime.tinnews.common.TinBasicActivity;
import com.coollime.tinnews.common.TinBasicFragment;

public class MainActivity extends TinBasicActivity {
    private ViewPager viewPager;
    private BottomNavigationView bottomBar;
    private TinFragmentPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewPager = findViewById(R.id.viewpager);
        adapter = new TinFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(TinFragmentPagerAdapter.FRAGMENT_NUMBER);
        bottomBar = findViewById(R.id.bottom_navigation);
        bottomBar.setOnNavigationItemSelectedListener(item -> {
            viewPager.setCurrentItem(ContainerFragment.getPositionById(item.getItemId()));
            return true;
        });
    }

    // Get FragmentManager of corresponding tabs
    private FragmentManager getCurrentChildFragmentManager() {
        return adapter.getItem(viewPager.getCurrentItem()).getChildFragmentManager();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void doFragmentTransaction(TinBasicFragment basicFragment) {
        FragmentTransaction fragmentTransaction = getCurrentChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(
                R.id.child_fragment_container,
                basicFragment,
                basicFragment.getFragmentTag()).addToBackStack(null).commit();
    }

    // To make the back button works as normal
    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getCurrentChildFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void showSnackBar(String message) {}
}