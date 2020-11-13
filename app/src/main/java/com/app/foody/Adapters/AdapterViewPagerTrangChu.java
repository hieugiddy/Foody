package com.app.foody.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.app.foody.Fragments.GantoiFragment;
import com.app.foody.Fragments.XemgantoiFragment;


public class AdapterViewPagerTrangChu extends FragmentStatePagerAdapter {
    XemgantoiFragment xemgantoiFragment;
    GantoiFragment gantoiFragment;
    public AdapterViewPagerTrangChu(@NonNull FragmentManager fm) {
        super(fm);
        xemgantoiFragment = new XemgantoiFragment();
        gantoiFragment = new GantoiFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return xemgantoiFragment;
            case 1:
                return gantoiFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
