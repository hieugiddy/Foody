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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Controller.XemGanToiControler;
import com.app.foody.R;

import static android.content.Context.MODE_PRIVATE;


public class XemGanToiFragment extends Fragment {
    XemGanToiControler ganToiController;
    RecyclerView recyclerxemgantoi;
    ProgressBar progressBarGanToi;
    SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_gantoi,container,false);
        recyclerxemgantoi=   (RecyclerView)view.findViewById(R.id.recyclerxemgantoi);
        progressBarGanToi=view.findViewById(R.id.progressBarGanToi);
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
        ganToiController=new XemGanToiControler(getContext());
        ganToiController.getDanhSachQuanAnController(recyclerxemgantoi,progressBarGanToi,viTriHienTai);
    }
}
