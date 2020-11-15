package com.app.foody.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.app.foody.Adapters.AdapterViewPagerTrangChu;
import com.app.foody.R;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener,View.OnClickListener {
    Spinner spinnerDanhSach;
    ViewPager viewPagerTrangChu;
    RadioButton xemganday, gantoi;
    RadioGroup group_hienthi;
    ArrayList<String> arrayList;
    TextView selection;
    CardView khamPha,giaoHang,datCho;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_home, container, false);

        khamPha=v.findViewById(R.id.khampha);
        giaoHang=v.findViewById(R.id.giaohang);
        datCho=v.findViewById(R.id.datcho);
        //slide banner
        final ImageSlider imageSlider = v.findViewById(R.id.slide); // init imageSlider
        final List<SlideModel> imageList = new ArrayList<>(); // Create image list
        selection =(TextView) v.findViewById(R.id.selection);
        FirebaseDatabase.getInstance().getReference().child("slide")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot data:dataSnapshot.getChildren())
                            imageList.add(new SlideModel(data.child("url").getValue().toString(), ScaleTypes.CENTER_CROP));
                        imageSlider.setImageList(imageList,ScaleTypes.CENTER_CROP);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        //

        //chọn địa chỉ
        spinnerDanhSach=v.findViewById(R.id.spChiNhanh);
        arrayList = new ArrayList<String>();
        arrayList.add("Đà Nẵng");
        arrayList.add("HCM");
        arrayList.add("Hà Nội");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_single_choice,arrayList);
        spinnerDanhSach.setAdapter(arrayAdapter);

        //Bắt sự kiện cho Spinner, khi chọn phần tử nào thì hiển thị lên Toast
        spinnerDanhSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //đối số postion là vị trí phần tử trong list Data
                selection.setText(arrayList.get(position));
                Toast.makeText(getActivity(),  arrayList.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "onNothingSelected", Toast.LENGTH_SHORT).show();
            }
        }); //

        khamPha.setOnClickListener(this);
        giaoHang.setOnClickListener(this);
        datCho.setOnClickListener(this);
        //xem gan đây, gần tôi
        viewPagerTrangChu = (ViewPager) v.findViewById(R.id.viewpager_trangchu);
        xemganday = (RadioButton) v.findViewById(R.id.xemganday);
        gantoi = (RadioButton) v.findViewById(R.id.gantoi);
        group_hienthi = (RadioGroup) v.findViewById(R.id.group_hienthi);

        AdapterViewPagerTrangChu adapterViewPagerTrangChu = new AdapterViewPagerTrangChu(getChildFragmentManager());
        viewPagerTrangChu.setAdapter(adapterViewPagerTrangChu);
        viewPagerTrangChu.addOnPageChangeListener(this);
        group_hienthi.setOnCheckedChangeListener(this);
        return v;
    }
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }


    public void onPageScrollStateChanged(int state) {

    }


    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.xemganday:
                viewPagerTrangChu.setCurrentItem(0);
                break;
            case R.id.gantoi:
                viewPagerTrangChu.setCurrentItem(1);
                break;
        }
    }
// sự kiện click mở Frament khác trong Frament
    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.khampha:
                fragment = new KhamPhaFragment();
                replaceFragment(fragment);
                break;

           case R.id.giaohang:
                fragment = new GiaoHangFragment();
                replaceFragment(fragment);
                break;
            case R.id.datcho:
                fragment = new DatChoFragment();
                replaceFragment(fragment);
                break;
        }
    }
    public void replaceFragment(Fragment someFragment) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_chinh, someFragment, "findThisFragment")
                .addToBackStack(null)
                .commit();
    }
    // kết thúc click frament
}