package com.app.foody.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.app.foody.Fragments.XemGanToiFragment;
import com.app.foody.Fragments.XemGanDayFragment;


public class AdapterViewPagerTrangChu extends FragmentStatePagerAdapter {
    XemGanDayFragment xemGanDayFragment;
    XemGanToiFragment xemGanToiFragment;
    public AdapterViewPagerTrangChu(@NonNull FragmentManager fm) {
        super(fm);
        xemGanDayFragment = new XemGanDayFragment();
        xemGanToiFragment = new XemGanToiFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return xemGanDayFragment;
            case 1:
                return xemGanToiFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
