package com.kevinsong.optus.codetestoptus.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kevinsong.optus.codetestoptus.ui.PlaceholderFragment;


public class PlaceHolderPagerAdapter extends FragmentPagerAdapter {

    public PlaceHolderPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return 5;
    }


}
