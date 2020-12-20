package com.app.foody.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.app.foody.Fragments.DonHangFragment;
import com.app.foody.Fragments.HomeFragment;
import com.app.foody.Fragments.ProfileFragment;
import com.app.foody.Fragments.SavedFragment;
import com.app.foody.Fragments.ThongBaoFragment;

public class ViewPageAdapterTrangChu extends FragmentStatePagerAdapter {
    public ViewPageAdapterTrangChu(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new SavedFragment();
            case 2:
                return new DonHangFragment();
            case 3:
                return new ThongBaoFragment();
            case 4:
                return new ProfileFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
