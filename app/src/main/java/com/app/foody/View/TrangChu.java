package com.app.foody.View;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.app.foody.Adapters.ViewPageAdapterTrangChu;
import com.app.foody.Controller.NotificationHelper;
import com.app.foody.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TrangChu extends AppCompatActivity {

    private BottomNavigationView mNavigationView;
    private ViewPager mViewPager;
    NotificationHelper notificationHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu);

        mNavigationView = findViewById(R.id.bottom_nav);
        mViewPager = findViewById(R.id.view_pager);

        setUpViewPager();
        /*
        //tạo 1 thông báo đơn giản cho android 5+ -> 8-
       NotificationManager notificationManager=(NotificationManager) getApplicationContext().getSystemService(
                Context.NOTIFICATION_SERVICE
        );
        Uri linkNhac= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(
                TrangChu.this,getString(R.string.app_name)
        );
        builder.setContentTitle("Miễn phí vận chuyển với đơn hàng trên 50k");
        builder.setContentText("Với đơn hàng trên 50k trong nội thành Thành phố Đà Nẵng, bạn sẽ được miễn phí vận chuyển");
        builder.setSmallIcon(R.drawable.icon_foody);
        builder.setSound(linkNhac);
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        notificationManager.notify(1,builder.build());
        //kết thúc phần tạo thông báo
        tạo 1 thông báo đơn giản cho android 8+
        notificationHelper=new NotificationHelper(this);
        notificationHelper.notify("Miễn phí vận chuyển với đơn hàng trên 50k","Với đơn hàng trên 50k trong nội thành Thành phố Đà Nẵng, bạn sẽ được miễn phí vận chuyển");
        //kết thúc phần tạo thông báo */
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.bn_home:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.bn_daluu:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.bn_donhang:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.bn_thongbao:
                        mViewPager.setCurrentItem(3);
                        break;
                    case R.id.bn_profile:
                        mViewPager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });
    }
    private void setUpViewPager(){
        ViewPageAdapterTrangChu viewPageAdapter = new ViewPageAdapterTrangChu(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPageAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position){
                    case 0:
                        mNavigationView.getMenu().findItem(R.id.bn_home).setChecked(true);
                        break;
                    case 1:
                        mNavigationView.getMenu().findItem(R.id.bn_daluu).setChecked(true);
                        break;
                    case 2:
                        mNavigationView.getMenu().findItem(R.id.bn_donhang).setChecked(true);
                        break;
                    case 3:
                        mNavigationView.getMenu().findItem(R.id.bn_thongbao).setChecked(true);
                        break;
                    case 4:
                        mNavigationView.getMenu().findItem(R.id.bn_profile).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}