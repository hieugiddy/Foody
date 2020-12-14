package com.app.foody.Fragments;

import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Controller.OdauController;
import com.app.foody.R;
import com.google.android.gms.maps.model.LatLng;

import static android.content.Context.MODE_PRIVATE;


public class OdauFragment extends Fragment {
    OdauController odauController;
    RecyclerView recyclerOdau;
    ProgressBar progressBarOdau;
    SharedPreferences sharedPreferences;
    NestedScrollView nestedScrollViewOdau;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_odau,container,false);
        recyclerOdau=   (RecyclerView)view.findViewById(R.id.recyclerOdau);
        nestedScrollViewOdau=view.findViewById(R.id.nestedScrollViewOdau);
        progressBarOdau=view.findViewById(R.id.progressBarOdau);
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
         odauController=new OdauController(getContext());
         odauController.getDanhSachQuanAnController(nestedScrollViewOdau,recyclerOdau,progressBarOdau,viTriHienTai);
    }
}
