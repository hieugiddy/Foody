package com.app.foody.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Controller.OdauController;
import com.app.foody.R;


public class OdauFragment extends Fragment {
    OdauController odauController;
    RecyclerView recyclerOdau;
    ProgressBar progressBarOdau;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_odau,container,false);
        recyclerOdau=   (RecyclerView)view.findViewById(R.id.recyclerOdau);
        progressBarOdau=view.findViewById(R.id.progressBarOdau);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
     odauController=new OdauController(getContext());
     odauController.getDanhSachQuanAnController(recyclerOdau,progressBarOdau);
    }
}
