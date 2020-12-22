package com.app.foody.Controller;

import android.content.Context;
import android.location.Location;
import android.view.View;
import android.widget.ProgressBar;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.app.foody.Adapters.XemGanDayAdapter;
import com.app.foody.Controller.Interfaces.OdauInterfaces;
import com.app.foody.Model.QuanAnModel;
import com.app.foody.R;

import java.util.ArrayList;
import java.util.List;

public class xemgandayController {
    QuanAnModel quanAnModel;
    Context context;
    XemGanDayAdapter gridViewAdapter;
    int soItemTiepTheo=0,limit=3;

    public xemgandayController(Context context) {
        this.context=context;
        quanAnModel=new QuanAnModel();
    }

    public void getDanhSachQuanAnController(final RecyclerView recyclerOdau, final ProgressBar progressBar, final Location viTriHienTai){
         final List<QuanAnModel> quanAnModelList=new ArrayList<>();

       RecyclerView.LayoutManager layoutManager1=new GridLayoutManager(context,2);
        recyclerOdau.setLayoutManager(layoutManager1);
        gridViewAdapter =new XemGanDayAdapter(context,quanAnModelList, R.layout.custom_layout_recyclerview_xemganday);
        recyclerOdau.setAdapter(gridViewAdapter);

        //
     final OdauInterfaces odauInterface=new OdauInterfaces() {
         @Override
         public void getDanhSachQuanAnModel(QuanAnModel quanAnModel) {
             quanAnModelList.add(quanAnModel);
             gridViewAdapter.notifyDataSetChanged();
             progressBar.setVisibility(View.GONE);
         }

     };
        recyclerOdau.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(recyclerOdau.getChildAt(recyclerOdau.getChildCount()-1)!=null){
                    if(scrollY>=(recyclerOdau.getChildAt(recyclerOdau.getChildCount()-1).getMeasuredHeight()-recyclerOdau.getMeasuredHeight())) {
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
