package com.app.foody.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.app.foody.Fragments.FragmentBanChay_GiaoNhanh;
import com.app.foody.Fragments.FragmentDanhGia_GiaoNhanh;
import com.app.foody.Fragments.FragmentGanToi_GiaoNhanh;
import com.app.foody.Fragments.FragmentGiaoNhanh;

public class AdapterFragment_GiaoNhanh extends FragmentStatePagerAdapter {
    String list[] ={"Gần tới","Bán chạy","Đánh giá","Giao nhanh"};
    FragmentGanToi_GiaoNhanh fragmentGanToi;
    FragmentBanChay_GiaoNhanh fragmentBanChay;
    FragmentDanhGia_GiaoNhanh fragmentDanhGia;
    FragmentGiaoNhanh fragmentGiaoNhanh;
    public AdapterFragment_GiaoNhanh(@NonNull FragmentManager fm) {
        super(fm);
        fragmentGanToi = new FragmentGanToi_GiaoNhanh();
        fragmentBanChay = new FragmentBanChay_GiaoNhanh();
        fragmentDanhGia = new FragmentDanhGia_GiaoNhanh();
        fragmentGiaoNhanh = new FragmentGiaoNhanh();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return fragmentGanToi;
        }else if(position == 1){
            return fragmentBanChay;
        }else if(position == 2){
            return fragmentDanhGia;
        }else if(position == 3){
            return fragmentGiaoNhanh;
        }else{
        }
        return null;
    }
    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list[position];
    }
}
