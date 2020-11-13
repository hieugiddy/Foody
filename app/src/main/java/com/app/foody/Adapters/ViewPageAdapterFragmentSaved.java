package com.app.foody.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.app.foody.Fragments.Saved_All_Fragment;
import com.app.foody.Fragments.Saved_BaiViet_Fragment;
import com.app.foody.Fragments.Saved_DiaDiem_Fragment;
import com.app.foody.Fragments.Saved_HinhAnh_Fragment;

public class ViewPageAdapterFragmentSaved extends FragmentStatePagerAdapter {
    public ViewPageAdapterFragmentSaved(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Saved_All_Fragment();
            case 1:
                return new Saved_DiaDiem_Fragment();
            case 2:
                return new Saved_HinhAnh_Fragment();
            case 3:
                return new Saved_BaiViet_Fragment();
            default:
                return new Saved_All_Fragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Tất cả";
                break;
            case 1:
                title = "Địa điểm";
                break;
            case 2:
                title = "Hình ảnh";
                break;
            case 3:
                title = "Bài viết";
                break;
        }
        return title;
    }
}
