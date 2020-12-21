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
import java.util.List;

public class OdauController {
    QuanAnModel quanAnModel;
    Context context;
    QuanAnAdapter adapterRecyclerOdau;
    int soItemTiepTheo=0,limit=3;
    public OdauController(Context context) {
        this.context=context;
        quanAnModel=new QuanAnModel();
    }

    public void getDanhSachQuanAnController(final NestedScrollView nestedScrollView, RecyclerView recyclerOdau, final ProgressBar progressBar, final Location viTriHienTai){
        final List<QuanAnModel> quanAnModelList=new ArrayList<>();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);
        recyclerOdau.setLayoutManager(layoutManager);
        adapterRecyclerOdau=new QuanAnAdapter(context,quanAnModelList, R.layout.custom_layout_recyclerview_odau);
        recyclerOdau.setAdapter(adapterRecyclerOdau);
        final OdauInterfaces odauInterface=new OdauInterfaces() {
            @Override
            public void getDanhSachQuanAnModel(final QuanAnModel quanAnModel) {
                quanAnModelList.add(quanAnModel);
                adapterRecyclerOdau.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void getDanhSachQuanAnModel(Product quanAnModel) {

            }
        };

        nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(nestedScrollView.getChildAt(nestedScrollView.getChildCount()-1)!=null){
                    if(scrollY>=(nestedScrollView.getChildAt(nestedScrollView.getChildCount()-1).getMeasuredHeight()-nestedScrollView.getMeasuredHeight())) {
                        soItemTiepTheo+=limit;
                        quanAnModel.getDanhSachQuanAn(odauInterface, viTriHienTai, soItemTiepTheo, soItemTiepTheo - limit);
                    }
                }

            }
        });
        soItemTiepTheo+=limit;
        quanAnModel.getDanhSachQuanAn(odauInterface, viTriHienTai, soItemTiepTheo, 0);
    }
}
