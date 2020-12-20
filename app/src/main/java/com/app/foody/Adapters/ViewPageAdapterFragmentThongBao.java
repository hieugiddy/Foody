package com.app.foody.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.app.foody.Fragments.ThongBao_CuaToi_Fragment;
import com.app.foody.Fragments.ThongBao_DichVu_Fragment;
import com.app.foody.Fragments.ThongBao_TinMoi_Fragment;

public class ViewPageAdapterFragmentThongBao extends FragmentStatePagerAdapter {
    public ViewPageAdapterFragmentThongBao(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ThongBao_DichVu_Fragment();
            case 1:
                return new ThongBao_CuaToi_Fragment();
            case 2:
                return new ThongBao_TinMoi_Fragment();
            default:
                return new ThongBao_DichVu_Fragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Dịch vụ";
                break;
            case 1:
                title = "Của tôi";
                break;
            case 2:
                title = "Tin mới";
                break;
        }
        return title;
    }
}
