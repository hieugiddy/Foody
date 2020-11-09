package com.app.foody.View;


import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.app.foody.Adapters.AdapterViewPagerTrangChu;
import com.app.foody.R;

public class TrangChuActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,RadioGroup.OnCheckedChangeListener {

    ViewPager viewPagerTrangChu;
    RadioButton rbOdau, rbAnGi;
    RadioGroup groupOdauAngi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);

        viewPagerTrangChu = (ViewPager)findViewById(R.id.viewpager_trangchu);
        rbOdau = (RadioButton)findViewById(R.id.rb_odau);
        rbAnGi = (RadioButton)findViewById(R.id.rb_angi);
        groupOdauAngi = (RadioGroup) findViewById(R.id.group_odau_angi);

        AdapterViewPagerTrangChu adapterViewPagerTrangChu = new AdapterViewPagerTrangChu(getSupportFragmentManager());
        viewPagerTrangChu.setAdapter(adapterViewPagerTrangChu);
        viewPagerTrangChu.addOnPageChangeListener(this);
        groupOdauAngi.setOnCheckedChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                rbOdau.setChecked(true);
                break;
            case 1:
                rbAnGi.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_angi:
                viewPagerTrangChu.setCurrentItem(1);
                break;
            case R.id.rb_odau:
                viewPagerTrangChu.setCurrentItem(0);
                break;
        }
    }

}
