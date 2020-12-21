package com.app.foody.Fragments;

import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Controller.AnGiController;
import com.app.foody.Controller.OdauController;
import com.app.foody.R;

import static android.content.Context.MODE_PRIVATE;


public class AngiFragment extends Fragment {
    AnGiController anGiController;
    RecyclerView recyclerAngi;
    ProgressBar progressBarAngi;
    SharedPreferences sharedPreferences;
    NestedScrollView nestedScrollViewAngi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_angi,container,false);
        recyclerAngi=   (RecyclerView)view.findViewById(R.id.recyclerAngi);
        nestedScrollViewAngi=view.findViewById(R.id.nestedScrollViewAngi);
        progressBarAngi=view.findViewById(R.id.progressBarAngi);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        sharedPreferences=getContext().getSharedPreferences("toado",MODE_PRIVATE);
        Double latitude=Double.parseDouble(sharedPreferences.getString("latitude","0"));
        Double longitude=Double.parseDouble(sharedPreferences.getString("longitude","0"));
        Location viTriHienTai=new Location("");
        viTriHienTai.setLatitude(latitude);
        viTriHienTai.setLongitude(longitude);
        anGiController=new AnGiController(getContext());
        anGiController.getDanhSachQuanAnController(nestedScrollViewAngi,recyclerAngi,progressBarAngi,viTriHienTai);
    }
}
