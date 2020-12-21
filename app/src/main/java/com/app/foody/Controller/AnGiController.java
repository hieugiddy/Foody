package com.app.foody.Controller;

import android.content.Context;
import android.location.Location;
import android.view.View;
import android.widget.ProgressBar;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Adapters.QuanAnAdapter;
import com.app.foody.Controller.Interfaces.OdauInterfaces;
import com.app.foody.Model.Product;
import com.app.foody.Model.QuanAnModel;
import com.app.foody.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnGiController {
    QuanAnModel quanAnModel;
    Context context;
    QuanAnAdapter adapterRecyclerAngi;
    
    public AnGiController(Context context) {
        this.context=context;
        quanAnModel=new QuanAnModel();
    }

    public void getDanhSachQuanAnController(final NestedScrollView nestedScrollView, RecyclerView recyclerOdau, final ProgressBar progressBar, final Location viTriHienTai){
        final List<QuanAnModel> quanAnModelList=new ArrayList<>();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);
        recyclerOdau.setLayoutManager(layoutManager);
        adapterRecyclerAngi=new QuanAnAdapter(context,quanAnModelList, R.layout.custom_layout_recyclerview_odau);
        recyclerOdau.setAdapter(adapterRecyclerAngi);
        final OdauInterfaces odauInterface=new OdauInterfaces() {
            @Override
            public void getDanhSachQuanAnModel(final QuanAnModel quanAnModel) {
                quanAnModelList.add(quanAnModel);
                Collections.sort(quanAnModelList);
                adapterRecyclerAngi.notifyDataSetChanged();
                Collections.sort(quanAnModelList);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void getDanhSachQuanAnModel(Product quanAnModel) {

            }
        };

        quanAnModel.getDanhSachQuanAn(odauInterface, viTriHienTai);
    }

}
