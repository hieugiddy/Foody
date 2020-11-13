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

import com.app.foody.R;
import com.app.foody.View.xemgandayController;


public class XemgantoiFragment  extends Fragment {
    com.app.foody.View.xemgandayController xemgandayController;
    RecyclerView recyclerOdau;
    ProgressBar progressBarOdau;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_xemganday, container, false);
        recyclerOdau=  view.findViewById(R.id.recyclerxemganday);
        progressBarOdau=view.findViewById(R.id.progressBarOdau);
        return view;
    }
    public void onStart() {
        super.onStart();
        xemgandayController=new xemgandayController(getContext());
        xemgandayController.getDanhSachQuanAnController(recyclerOdau,progressBarOdau);
    }
}
