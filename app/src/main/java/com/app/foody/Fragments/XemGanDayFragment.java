package com.app.foody.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.R;
import com.app.foody.Controller.xemgandayController;
import com.app.foody.View.ChiTietQuanAn;

import static android.content.Context.MODE_PRIVATE;


public class XemGanDayFragment  extends Fragment  {
    xemgandayController xemgandayController;
    RecyclerView recyclerOdau;
    ProgressBar progressBarOdau;
    SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_xemganday, container, false);
        recyclerOdau=  view.findViewById(R.id.recyclerxemganday);
        progressBarOdau=view.findViewById(R.id.progressBarOdau);
        return view;
    }
    public void onStart() {
        super.onStart();
        sharedPreferences=getContext().getSharedPreferences("toado",MODE_PRIVATE);
        Double latitude=Double.parseDouble(sharedPreferences.getString("latitude","0"));
        Double longitude=Double.parseDouble(sharedPreferences.getString("longitude","0"));
        Location viTriHienTai=new Location("");
        viTriHienTai.setLatitude(latitude);
        viTriHienTai.setLongitude(longitude);
        xemgandayController=new xemgandayController(getContext());
        xemgandayController.getDanhSachQuanAnController(recyclerOdau,progressBarOdau,viTriHienTai);
    }


}

